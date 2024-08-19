package cn.edu.zjut.domain.strategy.service.rule.chain.impl;

import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.edu.zjut.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.edu.zjut.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 黑名单责任链
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 14:21
 */
@Slf4j
@Component("rule_blacklist")
public class BlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = strategyRepository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        //100:user001,user002,user003
        // 过滤其他规则
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("抽奖责任链-黑名单接管 userId:{} strategyId:{} ruleModel:{} awardId:{}", userId, strategyId, ruleModel(), awardId);
                return DefaultChainFactory.StrategyAwardVO.builder()
                        .awardId(awardId)
                        .logicModel(ruleModel())
                        .build();
            }
        }

        // 过滤其他责任链
        log.info("抽奖责任链-黑名单未接管 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_BlACKLIST.getCode();
    }
}
