package com.xjtu.spider.spiders.baiduzhidao;

import com.xjtu.common.Config;
import com.xjtu.domain.domain.Domain;
import com.xjtu.domain.repository.DomainRepository;
import com.xjtu.facet.repository.FacetRepository;
import com.xjtu.spider.spiders.webmagic.bean.Assembles;
import com.xjtu.spider.spiders.webmagic.bean.FragmentContent;
import com.xjtu.spider.spiders.webmagic.pipeline.SqlPipeline;
import com.xjtu.spider.spiders.webmagic.service.SQLService;
import com.xjtu.spider.spiders.webmagic.spider.YangKuanSpider;
import com.xjtu.topic.domain.Topic;
import com.xjtu.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaiduZhidaoProcessor implements PageProcessor {

    @Autowired
    SQLService sqlService;


    private Site site = Site.me()
            .setRetryTimes(Config.retryTimes)
            .setRetrySleepTime(Config.retrySleepTime)
            .setSleepTime(Config.sleepTime)
            .setTimeOut(Config.timeOut)
            .addHeader("User-Agent", Config.userAgent)
            .addHeader("Accept", "*/*");

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        List<String> assembleContents = page.getHtml().xpath("pre[@class='best-text mb-10']").all();
        List<String> assembleTexts = page.getHtml().xpath("pre[@class='best-text mb-10']/tidyText()").all();
        Assembles assembles = new Assembles(assembleContents,assembleTexts);
        page.putField("assembles", assembles);
        //爬取碎片
        List<String> urls;
        urls = page.getHtml().xpath("dl[@class='dl']//a[@class='ti']/@href").all();
        //此处应该添加请求的附加信息，extras
        for (String url : urls) {
            Request request = new Request();
            request.setUrl(url);
            request.setExtras(page.getRequest().getExtras());
            page.addTargetRequest(request);
        }
    }
    public void baiduAnswerCrawl(String domainName){
        //1.获取分面信息
        List<Map<String, Object>> facets = sqlService.getFacets(domainName);
        //2.添加连接请求
        List<Request> requests = new ArrayList<>();
        for(Map<String, Object> facet : facets){
            Request request = new Request();
            String url = "https://zhidao.baidu.com/search?lm=0&rn=10&pn=0&fr=search&ie=gbk&word="
                    +facet.get("domainName")+" "
                    +facet.get("topicName")+" "
                    +facet.get("facetName");
            //添加链接;设置额外信息
            facet.put("sourceName", "百度知道");
            requests.add(request.setUrl(url).setExtras(facet));
        }
        YangKuanSpider.create(new BaiduZhidaoProcessor())
                .addRequests(requests)
                .thread(Config.THREAD)
                .addPipeline(new SqlPipeline())
                .addPipeline(new ConsolePipeline())
                .runAsync();
    }
}
