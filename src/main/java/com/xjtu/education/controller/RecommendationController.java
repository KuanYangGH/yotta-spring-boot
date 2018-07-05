package com.xjtu.education.controller;

import com.xjtu.common.domain.Result;
import com.xjtu.common.domain.ResultEnum;
import com.xjtu.education.service.RecommendationService;
import com.xjtu.utils.HttpUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 主题推荐接口
 *
 * @author yangkuan
 */
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RecommendationService recommendationService;

    @GetMapping("/saveRecommendationByDomainIdAndUserId")
    @ApiOperation(value = "保存推荐主题", notes = "保存推荐主题")
    public ResponseEntity saveState(@RequestParam(name = "domainId") Long domainId
            , @RequestParam(name = "recommendationTopics") String recommendationTopics
            , @RequestParam(name = "userId") Long userId
            , HttpServletRequest request) {
        logger.info(HttpUtil.getHeaders(request));
        Result result = recommendationService.saveRecommendation(domainId, recommendationTopics, userId);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/saveRecommendationByDomainNameAndUserId")
    @ApiOperation(value = "保存推荐主题", notes = "保存推荐主题")
    public ResponseEntity saveState(@RequestParam(name = "domainName") String domainName
            , @RequestParam(name = "recommendationTopics") String recommendationTopics
            , @RequestParam(name = "userId") Long userId
            , HttpServletRequest request) {
        logger.info(HttpUtil.getHeaders(request));
        Result result = recommendationService.saveRecommendation(domainName, recommendationTopics, userId);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getByDomainIdAndUserId")
    @ApiOperation(value = "查询推荐主题", notes = "查询推荐主题")
    public ResponseEntity getByDomainIdAndUserId(@RequestParam(name = "domainId") Long domainId
            , @RequestParam(name = "userId") Long userId
            , HttpServletRequest request) {
        logger.info(HttpUtil.getHeaders(request));
        Result result = recommendationService.findByDomainIdAndUserId(domainId, userId);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
