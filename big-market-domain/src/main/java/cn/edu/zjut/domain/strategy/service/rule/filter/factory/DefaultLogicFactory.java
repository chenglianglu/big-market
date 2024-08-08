package cn.edu.zjut.domain.strategy.service.rule.filter.factory;

import cn.edu.zjut.domain.strategy.model.entity.RuleActionEntity;
import cn.edu.zjut.domain.strategy.service.annotation.LogicStrategy;
import cn.edu.zjut.domain.strategy.service.rule.filter.ILogicFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 16:18
 */
@Service
public class DefaultLogicFactory {


    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        logicFilters.forEach(logic -> {
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (null != strategy) {
                logicFilterMap.put(strategy.logicMode().getCode(), logic);
            }
        });
    }

    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WEIGHT("rule_weight","【抽奖前规则】根据抽奖权重返回可抽奖范围KEY", "before"),
        RULE_BLACKLIST("rule_blacklist","【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回", "before"),
        RULE_LOCK("rule_lock","【抽奖中规则】抽奖n次后,对应奖品可解锁抽奖", "center"),
        RULE_LUCK_AWARD("rule_luck_award","【抽奖后规则】幸运奖兜底", "after"),

        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isCenter(String code) {
            return "center".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }
    }

}