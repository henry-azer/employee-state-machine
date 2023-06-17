package com.henry.employeestatemachine.controller;

import com.henry.employeestatemachine.controller.base.BaseController;
import com.henry.employeestatemachine.dto.EmployeeDto;
import com.henry.employeestatemachine.dto.base.response.ApiResponse;
import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController implements BaseController<EmployeeService> {
    private final EmployeeService employeeService;

    @Override
    public EmployeeService getService() {
        return employeeService;
    }

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable Long id) {
        log.info("EmployeeController: findById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Employee fetched successfully.", getService().findById(id));
    }

    @PostMapping
    public ApiResponse create(@RequestBody EmployeeDto employeeDto) {
        log.info("EmployeeController: create() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Employee created successfully.", getService().create(employeeDto));
    }

    @PutMapping("/{id}/event/{event}")
    public ApiResponse applyEvent(@PathVariable Long id, @PathVariable EmployeeEvents event) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Employee event applied successfully.", getService().applyEmployeeEvent(id, event));
    }
}
