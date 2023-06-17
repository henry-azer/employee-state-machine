package com.henry.employeestatemachine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.henry.employeestatemachine.dto.base.BaseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends BaseDto {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> status = new ArrayList<>();
    @JsonIgnore
    private List<String> events = new ArrayList<>();
}
