package com.henry.employeestatemachine.service;

import com.henry.employeestatemachine.dao.EmployeeDao;
import com.henry.employeestatemachine.dto.EmployeeDto;
import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.enums.EmployeeStates;
import com.henry.employeestatemachine.manager.EmployeeEventManager;
import com.henry.employeestatemachine.model.Employee;
import com.henry.employeestatemachine.transformer.EmployeeTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.AttributeConverter;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final EmployeeTransformer employeeTransformer;
    private final EmployeeEventManager employeeEventManager;
    private final AttributeConverter<List<String>, String> attributeConverter;

    @Override
    public EmployeeTransformer getTransformer() {
        return employeeTransformer;
    }

    @Override
    public EmployeeDao getDao() {
        return employeeDao;
    }

    @Override
    public EmployeeDto findById(Long id) {
        log.info("EmployeeService: findById(" + id + ") - called");
        Optional<Employee> employee = getDao().findById(id);
        if (employee.isEmpty()) throw new EntityNotFoundException("Employee not found. id: " + id);
        return getTransformer().transformEntityToDto(employee.get());
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        log.info("EmployeeService: create(dto) - called");
        Employee employee = getTransformer().transformDtoToEntity(dto);
        employee.setStatus(List.of(EmployeeStates.ADDED.toString()));
        return getTransformer().transformEntityToDto(getDao().create(employee));
    }

    @Override
    @Transactional
    public EmployeeDto applyEmployeeEvent(Long employeeId, EmployeeEvents event) {
        log.info("EmployeeService: applyEmployeeEvent(" + employeeId + ", " + event + ") - called");
        EmployeeDto employeeDto = findById(employeeId);
        employeeDto.getEvents().add(event.toString());
        employeeEventManager.apply(employeeDto.getId(), employeeDto.getEvents());
        getDao().updateEmployeeEvents(employeeId, attributeConverter.convertToDatabaseColumn(employeeDto.getEvents()));
        return findById(employeeId);
    }
}
