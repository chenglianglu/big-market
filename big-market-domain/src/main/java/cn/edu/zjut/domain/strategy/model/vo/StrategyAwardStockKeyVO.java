package cn.edu.zjut.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 策略奖励库存key标识值对象
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/19 15:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    // 策略id
    private Long strategyId;
    // 奖品id
    private Integer awardId;
}
