package cn.helloallen.domain.salary.service;

import cn.helloallen.domain.salary.model.aggregate.AdjustSalaryApplyOrderAggregate;

/**
 * 当前场景简单，所以不需要额外的设计模式使用。但如果是复杂场景，必须考虑设计模式，否则代码都写到 SalaryAdjustApplyService 实现类里，那么将非常难维护。
 * 不要只是把聚合对象当充血模型，你的充血结构是整个 domain 下的每一个领域包，也就是让这里的状态与行为看做为一整个结构
 */
public interface ISalaryAdjustApplyService {
    String execSalaryAdjust(AdjustSalaryApplyOrderAggregate adjustSalaryApplyOrderAggregate);
}
