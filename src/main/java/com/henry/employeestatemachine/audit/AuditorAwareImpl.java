package com.henry.employeestatemachine.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Admin");
    }
}