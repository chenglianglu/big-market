package cn.edu.zjut.test.infrastructure;

import cn.edu.zjut.infrastructure.persistent.dao.IAwardDao;
import cn.edu.zjut.infrastructure.persistent.po.AwardPO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 奖品dao测试
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 16:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardDaoTest {
    @Resource
    private IAwardDao awardDao;

    @Test
    public void test_queryAwardList(){
        List<AwardPO> awards = awardDao.queryAwardList();
        log.info("测试结果:{}", JSON.toJSONString(awards));

    }

}
