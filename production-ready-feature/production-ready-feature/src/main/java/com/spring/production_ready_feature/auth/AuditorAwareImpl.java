package com.spring.production_ready_feature.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // get security context
        // get authentication
        // get the principle
        // get the username
        return Optional.of("Akash kewat");
    }
}
