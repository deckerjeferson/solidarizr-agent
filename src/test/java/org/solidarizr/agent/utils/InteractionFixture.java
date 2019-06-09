package org.solidarizr.agent.utils;

import org.solidarizr.agent.chat.repository.model.Interaction;

public class InteractionFixture {
    public static final Interaction OPEN_INTERACTION_FROM_DB_WITHOUT_CATEGORY = Interaction.builder()
            .id(1)
            .closed(false)
            .chat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_CATEGORY)
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE.getId()).build();

    public static final Interaction OPEN_INTERACTION_FROM_DB_WITHOUT_TARGET_AUDIENCE = Interaction.builder()
            .id(2)
            .closed(false)
            .chat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY.getId()).build();

    public static final Interaction OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED = Interaction.builder()
            .id(3)
            .closed(false)
            .chat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY.getId())
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE.getId()).build();
}
