package cn.edu.zjut.test.domain;

import cn.edu.zjut.domain.strategy.model.entity.RaffleAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.RaffleFactorEntity;
import cn.edu.zjut.domain.strategy.service.raffle.IRaffleStrategy;
import cn.edu.zjut.domain.strategy.service.rule.impl.RuleWeightLogicFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

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
    private RuleWeightLogicFilter ruleWeightLogicFilter;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 4500L);
    }

    @Test
    public void test_performRaffle() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("lcl")
                .strategyId(100002L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
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

}
