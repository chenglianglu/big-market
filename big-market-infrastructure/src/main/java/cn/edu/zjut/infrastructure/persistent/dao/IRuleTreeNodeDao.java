package cn.edu.zjut.infrastructure.persistent.dao;

import cn.edu.zjut.infrastructure.persistent.po.RuleTreeNodePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/10 14:57
 */
@Mapper
public interface IRuleTreeNodeDao {
    List<RuleTreeNodePO> queryRuleTreeNodeListByTreeId(String treeId);
}
