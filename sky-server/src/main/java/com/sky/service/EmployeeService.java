package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

/*
 *

 * @author lpl
 * @create 2023/8/26
 **/
public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void add(EmployeeDTO employeeDTO);


    PageResult pageQueryEmploee(EmployeePageQueryDTO employeePageQueryDTO);

    void startorabondon(int status, long id);

    Employee selectById(long id);

    
    void editEmployee(EmployeeDTO employeeDTO);
}
