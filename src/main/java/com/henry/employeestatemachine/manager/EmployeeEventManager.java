package com.henry.employeestatemachine.manager;

import java.util.List;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
public interface EmployeeEventManager {
    Boolean apply(Long employeeId, List<String> events);
}