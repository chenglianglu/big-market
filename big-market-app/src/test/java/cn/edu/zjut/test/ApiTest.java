package cn.edu.zjut.test;

import cn.edu.zjut.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    @Resource
    private IRedisService redisService;

    @Test
    public void testRedis(){
        redisService.addToMap("strategy_id_100001", "1", "101");
        redisService.addToMap("strategy_id_100001", "2", "102");
        redisService.addToMap("strategy_id_100001", "3", "103");
        redisService.addToMap("strategy_id_100001", "4", "104");
        log.info("测试结果:{}", redisService.getFromMap("strategy_id_100001", "1"));
    }

}
