package cn.helloallen.infrastructure.employee.dao;
import cn.helloallen.infrastructure.employee.po.EmployeePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmployeeDAO {

    void insert(EmployeePO employee);

    void insertList(List<EmployeePO> list);

    void update(EmployeePO employeePO);

    EmployeePO queryEmployeeByEmployNumber(String employNumber);

}
