package cn.edu.zjut.domain.strategy.model;

import lombok.Builder;
import lombok.Data;
import org.checkerframework.checker.lock.qual.GuardedBy;

import java.math.BigDecimal;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/4 15:11
 */
@Data
@Builder
public class StrategyAwardEntity {

    /**抽奖策略ID */
    private Long strategyId;
    /**抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /**奖品库存总量 */
    private Integer awardCount;
    /**奖品库存剩余 */
    private Integer awardCount_surplus;
    /**奖品中奖概率 */
    private BigDecimal awardRate;
}
