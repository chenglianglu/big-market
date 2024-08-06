package cn.edu.zjut.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 规则事项实体类
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleMatterEntity {
    private String userId;
    /**抽奖策略ID */
    private Long strategyId;
    /**抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /**抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleModel;

}
