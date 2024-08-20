package cn.edu.zjut.trigger.job;

import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import cn.edu.zjut.domain.strategy.service.IRaffleStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 更新奖品库存定时任务;更新奖品库存任务;为了不让更新库存的压力打到数据库中，这里采用了redis更新缓存库存，异步队列更新数据库，数据库表最终一致即可。
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/20 15:11
 */
@Slf4j
@Component()
public class UpdateAwardStockJob {

    @Resource
    private IRaffleStock raffleStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        try {
            log.info("定时任务,更新奖品消耗库存[延迟队列获取], 降低对数据库的更新频次, 不要产生竞争");
            StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
            if (strategyAwardStockKeyVO == null) {
                return;
            }
            log.info("定时任务,更新奖品消耗库存 strategyId:{}, awardId:{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
            raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
        } catch (Exception e) {
            log.error("定时任务,更新奖品消耗库存[延迟队列获取], 异常:{}", e.getMessage());
        }


    }

}
