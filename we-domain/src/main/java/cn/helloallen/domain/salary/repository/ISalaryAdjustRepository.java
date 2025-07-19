package cn.helloallen.domain.salary.repository;

import cn.helloallen.domain.salary.model.aggregate.AdjustSalaryApplyOrderAggregate;

/**
 * 仓储在 DDD 中的设计，是一种依赖倒置关系，由 domain 定义接口，之后由引入 domain 包的基层层 infrastructure 实现功能。
 * 此外，因为是依赖倒置，所以天然的隔离了 PO 数据库持久化对象，不会被对外使用。这个设计是非常巧妙的。当我们从结构上定义了原则，就不会有人乱引用对象了
 */
public interface ISalaryAdjustRepository {
    String adjustSalary(AdjustSalaryApplyOrderAggregate adjustSalaryApplyOrderAggregate);
}
