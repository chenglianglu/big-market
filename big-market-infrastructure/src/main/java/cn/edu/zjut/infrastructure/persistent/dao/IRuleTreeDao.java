package cn.edu.zjut.infrastructure.persistent.dao;


import cn.edu.zjut.infrastructure.persistent.po.RuleTreePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/10 14:57
 */
@Mapper
public interface IRuleTreeDao {

    RuleTreePO queryRuleTreeByTreeId(String treeId);
}
