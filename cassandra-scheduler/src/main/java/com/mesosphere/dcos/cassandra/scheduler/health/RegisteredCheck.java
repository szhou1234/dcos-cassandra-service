package com.mesosphere.dcos.cassandra.scheduler.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.mesosphere.dcos.cassandra.scheduler.config.IdentityManager;

import java.util.Optional;


public class RegisteredCheck extends HealthCheck {
    public static final String NAME = "registered";
    private final IdentityManager manager;

    @Inject
    public RegisteredCheck(final IdentityManager manager) {
        this.manager = manager;
    }

    protected Result check() throws Exception {
        Optional<String> id = manager.get().getId();
        if (id.isPresent()) {
            return Result.healthy("Framework registered with id = " + id.get());
        }
        return Result.unhealthy("Framework is not yet registered");
    }
}