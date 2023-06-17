package com.henry.employeestatemachine.statemachine;

import com.henry.employeestatemachine.enums.EmployeeEvents;
import com.henry.employeestatemachine.enums.EmployeeStates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableStateMachineFactory
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmployeeStateMachineFactory extends EnumStateMachineConfigurerAdapter<EmployeeStates, EmployeeEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<EmployeeStates, EmployeeEvents> config) throws Exception {
        config.withConfiguration().autoStartup(true).listener(new EmployeeStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<EmployeeStates, EmployeeEvents> states) throws Exception {
        states
                .withStates()
                .initial(EmployeeStates.ADDED)
                .fork(EmployeeStates.IN_CHECK)
                .join(EmployeeStates.ALL_CHECKS_FINISHED)
                .state(EmployeeStates.APPROVED)
                .end(EmployeeStates.ACTIVE)
                .and()
                .withStates()
                .parent(EmployeeStates.IN_CHECK)
                .initial(EmployeeStates.SECURITY_CHECK_STARTED)
                .end(EmployeeStates.SECURITY_CHECK_FINISHED)
                .and()
                .withStates()
                .parent(EmployeeStates.IN_CHECK)
                .initial(EmployeeStates.WORK_PERMIT_CHECK_STARTED)
                .state(EmployeeStates.WORK_PERMIT_CHECK_PENDING_VERIFICATION)
                .end(EmployeeStates.WORK_PERMIT_CHECK_FINISHED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EmployeeStates, EmployeeEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(EmployeeStates.ADDED).target(EmployeeStates.IN_CHECK).event(EmployeeEvents.BEGIN_CHECK)
                .and()
                .withExternal()
                .source(EmployeeStates.ALL_CHECKS_FINISHED).target(EmployeeStates.APPROVED)
                .and()
                .withExternal()
                .source(EmployeeStates.APPROVED).target(EmployeeStates.ACTIVE).event(EmployeeEvents.ACTIVATE)
                .and()
                .withFork()
                .source(EmployeeStates.IN_CHECK)
                .target(EmployeeStates.SECURITY_CHECK_STARTED)
                .target(EmployeeStates.WORK_PERMIT_CHECK_STARTED)
                .and()
                .withExternal()
                .source(EmployeeStates.WORK_PERMIT_CHECK_STARTED).target(EmployeeStates.WORK_PERMIT_CHECK_PENDING_VERIFICATION).event(EmployeeEvents.COMPLETE_INITIAL_WORK_PERMIT_CHECK)
                .and()
                .withExternal()
                .source(EmployeeStates.WORK_PERMIT_CHECK_PENDING_VERIFICATION).target(EmployeeStates.WORK_PERMIT_CHECK_FINISHED).event(EmployeeEvents.FINISH_WORK_PERMIT_CHECK)
                .and()
                .withExternal()
                .source(EmployeeStates.SECURITY_CHECK_STARTED).target(EmployeeStates.SECURITY_CHECK_FINISHED).event(EmployeeEvents.FINISH_SECURITY_CHECK)
                .and()
                .withJoin()
                .source(EmployeeStates.SECURITY_CHECK_FINISHED)
                .source(EmployeeStates.WORK_PERMIT_CHECK_FINISHED)
                .target(EmployeeStates.ALL_CHECKS_FINISHED);
    }
}