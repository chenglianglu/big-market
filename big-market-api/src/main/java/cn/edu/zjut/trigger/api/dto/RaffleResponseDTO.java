package cn.edu.zjut.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 抽奖应答结果
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 14:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleResponseDTO {
    private Integer awardId;

    private Integer awardIndex;
}
