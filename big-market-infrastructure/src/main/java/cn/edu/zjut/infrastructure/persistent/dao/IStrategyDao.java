package cn.edu.zjut.infrastructure.persistent.dao;

import cn.edu.zjut.infrastructure.persistent.po.StrategyPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略DAO
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 15:56
 */
@Mapper
public interface IStrategyDao {
    List<StrategyPO> queryStrategyList();
}
