package org.solidarizr.agent.utils;

import org.solidarizr.agent.connector.model.TargetAudience;

public class TargetAudienceFixtures {
    public static final TargetAudience EXISTING_TARGET_AUDIENCE = TargetAudience.builder()
            .id(1)
            .name("Crianças").build();

    public static final TargetAudience ANOTHER_EXISTING_TARGET_AUDIENCE = TargetAudience.builder()
            .id(2)
            .name("Moradores de Rua").build();

}
