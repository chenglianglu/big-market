package cn.edu.zjut.domain.strategy.service.rule;

import cn.edu.zjut.domain.strategy.model.entity.RuleActionEntity;
import cn.edu.zjut.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @description: 抽奖规则过滤接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 15:52
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
