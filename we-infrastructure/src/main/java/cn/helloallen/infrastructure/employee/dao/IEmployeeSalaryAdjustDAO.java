package cn.helloallen.infrastructure.employee.dao;
import cn.helloallen.infrastructure.employee.po.EmployeeSalaryAdjustPO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IEmployeeSalaryAdjustDAO {

    void insert(EmployeeSalaryAdjustPO employeeSalaryAdjustPO);

}
