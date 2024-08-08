package cn.edu.zjut.domain.strategy.service.rule.chain;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 14:16
 */
public abstract class AbstractLogicChain implements ILogicChain{

    private ILogicChain next;


    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    protected abstract String ruleModel();
}
