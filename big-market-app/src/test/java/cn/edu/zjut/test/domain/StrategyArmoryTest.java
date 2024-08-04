package cn.edu.zjut.test.domain;

import cn.edu.zjut.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/4 16:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Test
    public void test_strategyArmory(){
        strategyArmory.assembleLotteryStrategy(100002L);
        log.info("策略装配完成");
    }

    @Test
    public void test_getAssembleRandomVal(){
        log.info("测试结果:{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果:{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果:{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果:{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
    }
}
