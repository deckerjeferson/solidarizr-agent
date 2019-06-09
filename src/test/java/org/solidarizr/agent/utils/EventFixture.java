package org.solidarizr.agent.utils;

import org.solidarizr.agent.connector.model.Event;

public class EventFixture {
    public static final Event EVENT_WITH_ALL_INFORMATIONS_FILLED = Event.builder()
            .name("Event 1")
            .description("Wow description")
            .phone("(51) 999999999")
            .site("http://www.google.com")
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY)
            .email("email@email.com").build();

    public static final Event EVENT_MISSING_EMAIL = Event.builder()
            .name("Event Missing Email")
            .description("Missing email event")
            .phone("(51) 999999991")
            .site("http://www.google.com/")
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY)   .build();

    public static final Event EVENT_MISSING_SITE = Event.builder()
            .name("Event Missing Site")
            .description("Missing email site")
            .phone("(51) 999999992")
            .email("email@email.com")
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY).build();

    public static final Event EVENT_MISSING_PHONE = Event.builder()
            .name("Event Missing Phone")
            .description("Event missing phone")
            .site("http://www.google.com")
            .targetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE)
            .category(CategoryFixtures.EXISTING_CATEGORY)
            .email("email@email.com").build();
}
