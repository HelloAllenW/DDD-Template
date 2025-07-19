package cn.helloallen.domain.salary.model.entity;

import cn.helloallen.domain.salary.model.valobj.EmployeePostVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 雇员实体对象
 * 实体对象是对数据库对象的抽象，大多数时候是 1:1 的关系结构，在一些复杂的模型场景中会是1:n的结构。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    /** 雇员级别 */
    private EmployeePostVO employeeLevel;

    /** 雇员岗位Title */
    private EmployeePostVO employeeTitle;

}