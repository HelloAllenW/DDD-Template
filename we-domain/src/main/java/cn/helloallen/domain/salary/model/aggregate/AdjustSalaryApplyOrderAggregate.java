package cn.helloallen.domain.salary.model.aggregate;

import cn.helloallen.domain.salary.model.entity.EmployeeEntity;
import cn.helloallen.domain.salary.model.entity.EmployeeSalaryAdjustEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调薪受理单聚合对象
 * 聚合对象是对实体对象和值对象的封装，代表着一类业务的聚合。通常是作为 service 服务层中入参出现。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdjustSalaryApplyOrderAggregate {

    /** 雇员编号 */
    private String employeeNumber;

    /** 调薪单号 */
    private String orderId;

    /** 雇员实体 */
    private EmployeeEntity employeeEntity;

    /** 雇员调薪实体 */
    private EmployeeSalaryAdjustEntity employeeSalaryAdjustEntity;

}
