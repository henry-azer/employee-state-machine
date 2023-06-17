package com.henry.employeestatemachine.transformer;

import com.henry.employeestatemachine.dto.EmployeeDto;
import com.henry.employeestatemachine.model.Employee;
import com.henry.employeestatemachine.transformer.base.BaseTransformer;
import com.henry.employeestatemachine.transformer.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Component
@AllArgsConstructor
public class EmployeeTransformer implements BaseTransformer<Employee, EmployeeDto, EmployeeMapper> {
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeMapper getMapper() {
        return employeeMapper;
    }
}
