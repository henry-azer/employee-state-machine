package com.henry.employeestatemachine.service;


import com.henry.employeestatemachine.dao.EmployeeDao;
import com.henry.employeestatemachine.dto.EmployeeDto;
import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.model.Employee;
import com.henry.employeestatemachine.service.base.BaseService;
import com.henry.employeestatemachine.transformer.EmployeeTransformer;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
public interface EmployeeService extends BaseService<Employee, EmployeeDto, EmployeeDao, EmployeeTransformer> {
    EmployeeDto applyEmployeeEvent(Long employeeId, EmployeeEvents event);
}
