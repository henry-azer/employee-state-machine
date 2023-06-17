package com.henry.employeestatemachine.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String timestamp;
    private String message;
    private Object body;
}
