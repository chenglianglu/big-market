package cn.edu.zjut.test.infrastructure;

import cn.edu.zjut.domain.strategy.model.vo.RuleTreeVO;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: 仓储数据查询
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/10 15:10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyRepositoryTest {
    @Resource
    private IStrategyRepository strategyRepository;

    @Test
    public void test_queryRuleTreeVOByTreeId() {
        RuleTreeVO ruleTreeVO = strategyRepository.queryRuleTreeVOByTreeId("tree_lock");
        log.info("测试结果:{}", JSON.toJSONString(ruleTreeVO));
    }
}
