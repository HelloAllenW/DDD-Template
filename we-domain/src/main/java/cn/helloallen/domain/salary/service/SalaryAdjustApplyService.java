package cn.helloallen.domain.salary.service;

import cn.helloallen.domain.salary.model.aggregate.AdjustSalaryApplyOrderAggregate;
import cn.helloallen.domain.salary.repository.ISalaryAdjustRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SalaryAdjustApplyService implements ISalaryAdjustApplyService {

    @Resource
    private ISalaryAdjustRepository salaryAdjustRepository;

    @Override
    public String execSalaryAdjust(AdjustSalaryApplyOrderAggregate adjustSalaryApplyOrderAggregate) {
        return salaryAdjustRepository.adjustSalary(adjustSalaryApplyOrderAggregate);
    }

}
