package cn.helloallen.domain.employee.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoEntity {

    /** 雇员编号 */
    private String employeeNumber;
    /** 雇员姓名 */
    private String employeeName;
    /** 雇员级别 */
    private String employeeLevel;
    /** 雇员岗位Title */
    private String employeeTitle;
    /** 薪资总额 */
    private BigDecimal salaryTotalAmount;
    /** 绩效工资 */
    private BigDecimal salaryMeritAmount;
    /** 基础工资 */
    private BigDecimal salaryBaseAmount;

}
