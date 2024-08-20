package cn.edu.zjut.test.domain;

import cn.edu.zjut.domain.strategy.model.entity.RaffleAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.RaffleFactorEntity;
import cn.edu.zjut.domain.strategy.service.armory.IStrategyArmory;
import cn.edu.zjut.domain.strategy.service.IRaffleStrategy;
import cn.edu.zjut.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import cn.edu.zjut.domain.strategy.service.rule.tree.impl.RuleLockLogicTreeNode;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 抽奖策略测试类
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/6 15:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IRaffleStrategy raffleStrategy;

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;

    @Resource
    private RuleLockLogicTreeNode ruleLockLogicTreeNode;


    @Before
    public void setUp() {
        // 策略装配 100001, 100002, 100003
//        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100001L));
//        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100002L));
//        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100003L));
        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100006L));
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4900L);
        ReflectionTestUtils.setField(ruleLockLogicTreeNode, "userRaffleCount", 0L);
    }

    @Test
    public void test_performRaffle() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                    .userId("lcl")
                    .strategyId(100006L)
                    .build();

            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

            log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
            log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
        }
        new CountDownLatch(1).await();
    }

    @Test
    public void test_performRaffle_blacklist() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("user003")  // 黑名单用户 user001,user002,user003
                .strategyId(100001L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_faffle_center_rule_lock(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("lcl")
                .strategyId(100003L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

}

