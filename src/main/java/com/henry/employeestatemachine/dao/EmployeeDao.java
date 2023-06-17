package com.henry.employeestatemachine.dao;

import com.henry.employeestatemachine.dao.base.BaseDao;
import com.henry.employeestatemachine.dao.repo.EmployeeRepo;
import com.henry.employeestatemachine.model.Employee;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
public interface EmployeeDao extends BaseDao<Employee, EmployeeRepo> {
    void updateEmployeeEvents(Long employeeId, String events);
    void updateEmployeeStatus(Long employeeId, String status);
}
