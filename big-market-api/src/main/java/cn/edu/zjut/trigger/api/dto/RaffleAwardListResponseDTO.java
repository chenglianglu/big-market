package cn.edu.zjut.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 抽奖奖品列表响应DTO
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 14:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleAwardListResponseDTO {

    private Integer awardId;

    private String awardTitle;

    private String awardSubtitle;

    private Integer sort;
}
