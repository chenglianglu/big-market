package cn.edu.zjut.test.domain;

import cn.edu.zjut.domain.strategy.service.armory.IStrategyArmory;
import cn.edu.zjut.domain.strategy.service.armory.IStrategyDispatch;
import cn.edu.zjut.domain.strategy.service.armory.StrategyArmoryDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Scanner;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/4 16:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryDispatchTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Before
    public void test_strategyArmory(){
        boolean success = strategyArmory.assembleLotteryStrategy(100002L);
        log.info("测试结果:{}", success);
    }

    @Test
    public void test_getAssembleRandomVal(){
        log.info("测试结果:{} - 奖品ID值", strategyDispatch.getRandomAwardId(100002L));
    }

    @Test
    public void test_getAssembleRuleWeightValue(){
        log.info("测试结果:{} - 4000策略配置", strategyDispatch.getRandomAwardId(100002L, "4000"));
        log.info("测试结果:{} - 5000策略配置", strategyDispatch.getRandomAwardId(100002L, "5000"));
        log.info("测试结果:{} - 6000策略配置", strategyDispatch.getRandomAwardId(100002L, "6000"));
    }
}
