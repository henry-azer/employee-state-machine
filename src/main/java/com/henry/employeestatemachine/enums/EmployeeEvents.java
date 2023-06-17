package com.henry.employeestatemachine.enums;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
public enum EmployeeEvents {
    BEGIN_CHECK("BEGIN_CHECK"),
    FINISH_SECURITY_CHECK("FINISH_SECURITY_CHECK"),
    COMPLETE_INITIAL_WORK_PERMIT_CHECK("COMPLETE_INITIAL_WORK_PERMIT_CHECK"),
    FINISH_WORK_PERMIT_CHECK("FINISH_WORK_PERMIT_CHECK"),
    ACTIVATE("ACTIVATE");

    public final String event;

    EmployeeEvents(String event) {
        this.event = event;
    }
}
