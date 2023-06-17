package com.henry.employeestatemachine.transformer.mapper;

import com.henry.employeestatemachine.dto.EmployeeDto;
import com.henry.employeestatemachine.model.Employee;
import com.henry.employeestatemachine.transformer.mapper.base.BaseMapper;
import com.henry.employeestatemachine.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
}
