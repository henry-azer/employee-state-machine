package com.henry.employeestatemachine.manager;

import com.henry.employeestatemachine.constants.EmployeeStateMachineConstants;
import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.enums.EmployeeStates;
import com.henry.employeestatemachine.statemachine.EmployeeStateMachineInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeEventManagerImpl implements EmployeeEventManager {
    private final StateMachineFactory<EmployeeStates, EmployeeEvents> stateMachineFactory;
    private final EmployeeStateMachineInterceptor employeeStateMachineInterceptor;

    @Override
    public Boolean apply(Long employeeId, List<String> events) {
        log.info("EmployeeEventManager: apply(" + employeeId + ", " + events + ") - called");
        StateMachine<EmployeeStates, EmployeeEvents> stateMachine = stateMachineFactory.getStateMachine(employeeId.toString());
        events.forEach(employeeEvent -> {
            Message<EmployeeEvents> message = getEmployeeEventsMessage(employeeId, stateMachine, employeeEvent);
            boolean isAccepted = stateMachine.sendEvent(message);
            if (!isAccepted)
                throw new IllegalArgumentException("Event (" + employeeEvent + ") not accepted for employee id: " + employeeId);
        });
        return true;
    }

    private Message<EmployeeEvents> getEmployeeEventsMessage(Long employeeId, StateMachine<EmployeeStates, EmployeeEvents> stateMachine, String employeeEvent) {
        Message<EmployeeEvents> message = MessageBuilder.withPayload(EmployeeEvents.valueOf(employeeEvent))
                .setHeader(EmployeeStateMachineConstants.EMPLOYEE_ID, employeeId)
                .setHeader(EmployeeStateMachineConstants.EVENT,employeeEvent).build();
        stateMachine.getStateMachineAccessor().doWithAllRegions(stateMachineAccess ->
                stateMachineAccess.addStateMachineInterceptor(employeeStateMachineInterceptor));
        return message;
    }
}