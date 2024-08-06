package cn.edu.zjut.domain.strategy.model.entity;

import cn.edu.zjut.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 策略规则实体类
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 14:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    /**抽奖策略ID */
    private Long strategyId;
    /**抽奖奖品ID【规则类型为策略，则不需要奖品ID】 */
    private Integer awardId;
    /**抽象规则类型；1-策略规则、2-奖品规则 */
    private Integer ruleType;
    /**抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleModel;
    /**抽奖规则比值 */
    private String ruleValue;
    /**抽奖规则描述 */
    private String ruleDesc;

    /**
     * 获取权重值
     * 数据案例;1000:102,103,104,1055000:102,103,104,105,106,1076000:102,103,104,105,106,107,108,109
     * @return
     */
    public Map<String, List<Integer>> getRuleWeightMap() {
        if (!"rule_weight".equals(ruleModel)) return null;
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> ruleWeightMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            // 检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) {
                return ruleWeightMap;
            }
            // 分割字符串以获取健和值
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }
            // 解析值
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            List<Integer> value = new ArrayList<>();
            for (String valueString : valueStrings) {
                value.add(Integer.parseInt(valueString));
            }
            ruleWeightMap.put(parts[0], value);

        }
        return ruleWeightMap;
    }

}
