package cn.helloallen.domain.employee.repository;

import cn.helloallen.domain.employee.model.entity.EmployeeInfoEntity;

public interface IEmployeeRepository {

    void insertEmployeeInfo(EmployeeInfoEntity employeeInfoEntity);

    EmployeeInfoEntity queryEmployInfo(String employNumber);

}
