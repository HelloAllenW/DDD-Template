package cn.helloallen.domain.employee.service;

import cn.helloallen.domain.employee.model.entity.EmployeeInfoEntity;
import cn.helloallen.domain.employee.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeService implements IEmployeeService{

    @Resource
    private IEmployeeRepository employeeRepository;

    @Override
    public void insertEmployInfo(EmployeeInfoEntity employeeInfoEntity) {
        employeeRepository.insertEmployeeInfo(employeeInfoEntity);
    }

    @Override
    public EmployeeInfoEntity queryEmployInfo(String employNumber) {
        return employeeRepository.queryEmployInfo(employNumber);
    }
}
