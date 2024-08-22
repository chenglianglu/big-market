package cn.edu.zjut.trigger.api.dto;

import lombok.Data;

/**
 * @description: 抽奖奖品列表, 请求对象
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 14:45
 */
@Data
public class RaffleAwardListRequestDTO {
    // 抽奖策略id
    private Long strategyId;
}
