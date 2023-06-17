package com.henry.employeestatemachine.dto.base.request;

import com.henry.employeestatemachine.dto.base.pagination.Page;
import com.henry.employeestatemachine.dto.base.pagination.SortingBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationRequest extends Page {
    private List<SortingBy> sortingByList;
}