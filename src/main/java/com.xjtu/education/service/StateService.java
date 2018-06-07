package com.xjtu.education.service;

import com.xjtu.common.domain.Result;
import com.xjtu.common.domain.ResultEnum;
import com.xjtu.domain.domain.Domain;
import com.xjtu.domain.repository.DomainRepository;
import com.xjtu.education.domain.State;
import com.xjtu.education.repository.StateRepository;
import com.xjtu.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 推荐方式服务层
 *
 * @author yangkuan
 */
@Service
public class StateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StateRepository stateRepository;

    @Autowired
    DomainRepository domainRepository;

    /**
     * 保存主题状态
     *
     * @param domainId
     * @param states
     * @param userId
     * @return
     */
    public Result saveRecommendation(Long domainId, String states
            , Long userId) {
        State state = stateRepository
                .findByDomainIdAndUserId(domainId, userId);
        if (state == null) {
            state = new State();
            state.setDomainId(domainId);
            state.setStates(states);
            state.setUserId(userId);
            state.setCreatedTime(new Date());
            state.setModifiedTime(new Date());
            stateRepository.save(state);
        } else {
            stateRepository.updateByDomainIdAndUserId(domainId
                    , userId
                    , states
                    , new Date());
        }
        return ResultUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "主题状态保存成功");
    }

    /**
     * 保存主题状态
     *
     * @param domainName
     * @param states
     * @param userId
     * @return
     */
    public Result saveRecommendation(String domainName, String states
            , Long userId) {
        Domain domain = domainRepository.findByDomainName(domainName);
        if (domain == null) {
            logger.info("保存失败:课程不存在");
            return ResultUtil.error(ResultEnum.RECOMMENDATION_INSERT_ERROR.getCode(), ResultEnum.RECOMMENDATION_INSERT_ERROR.getMsg());
        }
        return saveRecommendation(domain.getDomainId()
                , states
                , userId);
    }

    /**
     * 查询主题状态
     *
     * @param domainId
     * @param userId
     * @return
     */
    public Result findByDomainIdAndUserId(Long domainId, Long userId) {
        State state = stateRepository
                .findByDomainIdAndUserId(domainId, userId);
        logger.info("推荐主题查询成功");
        return ResultUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), state);
    }
}
