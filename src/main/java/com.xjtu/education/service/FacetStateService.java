package com.xjtu.education.service;

import com.xjtu.common.domain.Result;
import com.xjtu.common.domain.ResultEnum;
import com.xjtu.domain.domain.Domain;
import com.xjtu.domain.repository.DomainRepository;
import com.xjtu.education.domain.FacetState;
import com.xjtu.education.repository.FacetStateRepository;
import com.xjtu.topic.domain.Topic;
import com.xjtu.topic.repository.TopicRepository;
import com.xjtu.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yangkuan
 */
@Service
public class FacetStateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacetStateRepository facetStateRepository;

    @Autowired
    DomainRepository domainRepository;

    @Autowired
    TopicRepository topicRepository;
    /**
     * 保存分面状态
     *
     * @param domainId
     * @param topicId
     * @param states
     * @param userId
     * @return
     */
    public Result saveState(Long domainId, Long topicId,
                            String states, Long userId) {
        FacetState facetState = facetStateRepository.findByDomainIdAndTopicIdAndUserId(domainId, topicId, userId);
        if (facetState == null) {
            facetState = new FacetState();
            facetState.setDomainId(domainId);
            facetState.setTopicId(topicId);
            facetState.setStates(states);
            facetState.setUserId(userId);
            facetState.setCreatedTime(new Date());
            facetState.setModifiedTime(new Date());
            facetStateRepository.save(facetState);
        } else {
            facetStateRepository.updateByDomainIdAndTopicIdAndUserId(domainId
                    , topicId
                    , userId
                    , states
                    , new Date());
        }
        return ResultUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "分面状态保存成功");
    }

    /**
     * 保存主题状态
     *
     * @param domainName
     * @param topicName
     * @param states
     * @param userId
     * @return
     */
    public Result saveState(String domainName, String topicName, String states
            , Long userId) {
        Domain domain = domainRepository.findByDomainName(domainName);
        if (domain == null) {
            logger.error("保存失败:课程不存在");
            logger.error("public Result saveState(String domainName, String topicName, String states, Long userId)");
            return ResultUtil.error(ResultEnum.STATE_INSERT_ERROR.getCode(), ResultEnum.STATE_INSERT_ERROR.getMsg());
        }
        Topic topic = topicRepository.findByDomainIdAndTopicName(domain.getDomainId(), topicName);
        if (topic == null) {
            logger.error("保存失败:主题不存在");
            logger.error("public Result saveState(String domainName, String topicName, String states, Long userId)");
            return ResultUtil.error(ResultEnum.STATE_INSERT_ERROR.getCode(), ResultEnum.STATE_INSERT_ERROR.getMsg());
        }
        return saveState(domain.getDomainId()
                , topic.getTopicId()
                , states
                , userId);
    }

    /**
     * 查询分面状态
     *
     * @param domainId
     * @param userId
     * @return
     */
    public Result findByDomainIdAndTopicIdAndUserId(Long domainId, Long topicId, Long userId) {
        FacetState facetState = facetStateRepository.findByDomainIdAndTopicIdAndUserId(domainId, topicId, userId);
        logger.info("分面状态查询成功");
        return ResultUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), facetState);
    }

}
