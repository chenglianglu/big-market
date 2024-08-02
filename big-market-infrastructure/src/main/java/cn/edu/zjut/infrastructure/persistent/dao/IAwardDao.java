package cn.edu.zjut.infrastructure.persistent.dao;

import cn.edu.zjut.infrastructure.persistent.po.AwardPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 奖品表dao
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 15:56
 */
@Mapper
public interface IAwardDao {
    List<AwardPO> queryAwardList();
}
