package com.henry.employeestatemachine.dao.repo;

import com.henry.employeestatemachine.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employee SET events = :events WHERE id = :employeeId", nativeQuery = true)
    void updateEmployeeEvents(Long employeeId, String events);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employee SET status = :status WHERE id = :employeeId", nativeQuery = true)
    void updateEmployeeStatus(Long employeeId, String status);
}
