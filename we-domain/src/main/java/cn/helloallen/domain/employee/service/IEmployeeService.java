package cn.helloallen.domain.employee.service;

import cn.helloallen.domain.employee.model.entity.EmployeeInfoEntity;

public interface IEmployeeService {

    void insertEmployInfo(EmployeeInfoEntity employeeInfoEntity);

    EmployeeInfoEntity queryEmployInfo(String employNumber);

}
