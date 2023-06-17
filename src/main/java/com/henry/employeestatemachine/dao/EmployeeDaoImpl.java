package com.henry.employeestatemachine.dao;

import com.henry.employeestatemachine.dao.repo.EmployeeRepo;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private final EmployeeRepo employeeRepo;

    public EmployeeDaoImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeRepo getRepository() {
        return employeeRepo;
    }

    @Override
    public void updateEmployeeEvents(Long employeeId, String events) {
        getRepository().updateEmployeeEvents(employeeId, events);
    }

    @Override
    public void updateEmployeeStatus(Long employeeId, String status) {
        getRepository().updateEmployeeStatus(employeeId, status);
    }
}
