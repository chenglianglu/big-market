package cn.edu.zjut.domain.strategy.model.entity;

import cn.edu.zjut.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @description: 规则动作实体类
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 15:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();

    private String ruleModel;

    private T data;


    public static class RaffleEntity{

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RaffleBeforeEntity extends RaffleEntity{

        /**抽奖策略ID */
        private Long strategyId;
        /** 权重key值;用于抽奖时可以选择权重抽奖 */
        private String ruleWeightValueKey;
        /**抽奖奖品ID - 内部流转使用 */
        private Integer awardId;

    }

    public static class RaffleCenterEntity extends RaffleEntity{

    }
    public static class BeforeAfterEntity extends RaffleEntity{

    }
}
