package cn.edu.zjut.domain.strategy.service.rule.chain.impl;

import cn.edu.zjut.domain.strategy.service.armory.IStrategyDispatch;
import cn.edu.zjut.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 默认逻辑链
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 14:24
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer randomAwardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId:{} strategyId:{} ruleModel:{} randomAwardId:{}", userId, strategyId, ruleModel(), randomAwardId);
        return randomAwardId;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }
}
