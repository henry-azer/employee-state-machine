package com.henry.employeestatemachine.enums;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
public enum EmployeeStates {
    ADDED("ADDED"),
    IN_CHECK("IN_CHECK"),
    APPROVED("APPROVED"),
    ACTIVE("ACTIVE"),
    ALL_CHECKS_FINISHED("ALL_CHECKS_FINISHED"),
    SECURITY_CHECK_STARTED("SECURITY_CHECK_STARTED"),
    SECURITY_CHECK_FINISHED("SECURITY_CHECK_FINISHED"),
    WORK_PERMIT_CHECK_STARTED("WORK_PERMIT_CHECK_STARTED"),
    WORK_PERMIT_CHECK_PENDING_VERIFICATION("WORK_PERMIT_CHECK_PENDING_VERIFICATION"),
    WORK_PERMIT_CHECK_FINISHED("WORK_PERMIT_CHECK_FINISHED");

    public final String state;

    EmployeeStates(String state) {
        this.state = state;
    }
}
