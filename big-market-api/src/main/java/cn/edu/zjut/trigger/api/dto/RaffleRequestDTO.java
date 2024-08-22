package cn.edu.zjut.trigger.api.dto;

import lombok.Data;

/**
 * @description: 抽奖请求接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 14:50
 */
@Data
public class RaffleRequestDTO {
    // 抽奖策列id
    private Long strategyId;
}
