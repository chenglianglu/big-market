package cn.edu.zjut.trigger.api;

import cn.edu.zjut.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.edu.zjut.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.edu.zjut.trigger.api.dto.RaffleRequestDTO;
import cn.edu.zjut.trigger.api.dto.RaffleResponseDTO;
import cn.edu.zjut.types.model.Response;

import java.util.List;

/**
 * @description: 抽奖服务接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 14:41
 */
public interface IRaffleService {
    /**
     * 策略装配接口
     * @param strategyId
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表配置
     * @param requestDTO
     * @return
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     * @param requestDTO
     * @return
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);
}
