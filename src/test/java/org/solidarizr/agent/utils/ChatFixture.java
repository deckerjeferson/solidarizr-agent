package org.solidarizr.agent.utils;

import org.solidarizr.agent.chat.repository.model.Chat;

import java.time.chrono.HijrahChronology;
import java.util.List;

public class ChatFixture {
    public static final Chat CHAT_WITH_JUST_ID = Chat.builder().id(1L).build();

    public static final Chat CHAT_WITH_OPEN_INTERACTION_WITHOUT_CATEGORY = Chat.builder()
            .id(2L)
            .interactions(List.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITHOUT_CATEGORY)).build();

    public static final Chat CHAT_WITH_OPEN_INTERACTION_WITHOUT_TARGET_AUDIENCE = Chat.builder()
            .id(3L)
            .interactions(List.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITHOUT_TARGET_AUDIENCE)).build();

    public static final Chat CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED = Chat.builder()
            .id(4L)
            .interactions(List.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED)).build();
}
