package cn.edu.zjut.domain.strategy.service.rule.chain.factory;

import cn.edu.zjut.domain.strategy.model.entity.StrategyEntity;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.rule.chain.ILogicChain;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: 默认链工厂
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 15:21
 */
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;

    private final IStrategyRepository strategyRepository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
        this.logicChainGroup = logicChainGroup;
    }

    public ILogicChain openLogicChain(Long strategyId) {
        StrategyEntity strategy = strategyRepository.queryStrategyEntityByStrategyId(strategyId);

        String[] ruleModels = strategy.ruleModels();

        if (null == ruleModels || ruleModels.length == 0) {
            return logicChainGroup.get("default");
        }

        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            current = current.appendNext(nextChain);
        }
        current.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO{
        private  Integer awardId;
        private  String logicModel;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel{
        RULE_DEFAULT("rule_default", "默认抽奖"),
        RULE_BlACKLIST("rule_blacklist", "黑名单抽奖"),
        RULE_WEIGHT("rule_weight", "权重规则"),
        ;
        private final String code;
        private final String info;
    }
}
