package com.EventPulse.security.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component("auditorProvider")
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication() != null
                        ? org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication().getName()
                        : "SYSTEM"
        );
    }
}
