package com.henry.employeestatemachine.statemachine;

import com.henry.employeestatemachine.dao.EmployeeDao;
import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.enums.EmployeeStates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmployeeStateMachineInterceptor extends StateMachineInterceptorAdapter<EmployeeStates, EmployeeEvents> {
    private final EmployeeDao employeeDao;
    private final AttributeConverter<List<String>, String> attributeConverter;

    @Override
    public void postStateChange(State<EmployeeStates, EmployeeEvents> state, Message<EmployeeEvents> message,
                                Transition<EmployeeStates, EmployeeEvents> transition, StateMachine<EmployeeStates,
            EmployeeEvents> stateMachine, StateMachine<EmployeeStates, EmployeeEvents> rootStateMachine) {
        log.info("EmployeeStateMachineInterceptor: postStateChange() - stateMachine: " + stateMachine.getId() + " - called");
        List<String> employeeStatus = rootStateMachine.getState().getIds().stream().map(EmployeeStates::name).collect(Collectors.toList());
        employeeDao.updateEmployeeStatus(Long.valueOf((rootStateMachine.getId())), attributeConverter.convertToDatabaseColumn(employeeStatus));
        log.info("EmployeeStateMachineInterceptor: postStateChange() - stateMachine: " + stateMachine.getId() + " - ended");
    }

    @Override
    public Exception stateMachineError(StateMachine<EmployeeStates, EmployeeEvents> stateMachine, Exception exception) {
        log.info("EmployeeStateMachineInterceptor: stateMachineError() - stateMachine: "
                + stateMachine.getId() + " Exception: " + exception.getMessage());
        return super.stateMachineError(stateMachine, exception);
    }
}