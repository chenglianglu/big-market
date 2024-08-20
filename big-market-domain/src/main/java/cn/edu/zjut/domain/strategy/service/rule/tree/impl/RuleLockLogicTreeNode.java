package cn.edu.zjut.domain.strategy.service.rule.tree.impl;

import cn.edu.zjut.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.edu.zjut.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.edu.zjut.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 次数锁节点
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:28
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    private Long userRaffleCount = 10L;
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-次数锁 userId:{}, strategyId:{}, awardId:{}", userId, strategyId, awardId);
        long raffleCount = 0L;
        try {
            raffleCount = Long.parseLong(ruleValue);
        } catch (Exception e){
            throw new RuntimeException("规则过滤-次数锁异常 ruleValue:" + ruleValue + "配置不正确");
        }
        // 用户抽奖次数大于规则限定值, 规则放行
        if (userRaffleCount >= raffleCount){
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }
        // 用户抽奖次数小于规则限定值, 规则拦截
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
