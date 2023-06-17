package com.henry.employeestatemachine.statemachine;

import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.enums.EmployeeStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
public class EmployeeStateMachineListener extends StateMachineListenerAdapter<EmployeeStates, EmployeeEvents> {

    @Override
    public void stateChanged(State<EmployeeStates, EmployeeEvents> from, State<EmployeeStates, EmployeeEvents> to) {
        log.info("State Changed: From " + from + " - To " + to);
    }
}