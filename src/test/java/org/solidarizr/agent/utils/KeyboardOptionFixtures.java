package org.solidarizr.agent.utils;

import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.Messages;
import org.solidarizr.agent.messageHandler.intent.IntentCallbacks;

public class KeyboardOptionFixtures {
    public static final HandledMessage.Keyboard.Option HANDLED_MESSAGE_FROM_EXISTING_TARGET_AUDIENCE = HandledMessage.Keyboard.Option.builder()
            .id(String.format(IntentCallbacks.DEFINDED_TARGET_AUDIENCE, TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE.getId()))
            .option(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE.getName()).build();

    public static final HandledMessage.Keyboard.Option HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_TARGET_AUDIENCE = HandledMessage.Keyboard.Option.builder()
            .id(String.format(IntentCallbacks.DEFINDED_TARGET_AUDIENCE, TargetAudienceFixtures.ANOTHER_EXISTING_TARGET_AUDIENCE.getId()))
            .option(TargetAudienceFixtures.ANOTHER_EXISTING_TARGET_AUDIENCE.getName()).build();

    public static final HandledMessage.Keyboard.Option HANDLED_MESSAGE_FROM_EXISTING_CATEGORY = HandledMessage.Keyboard.Option.builder()
            .id(String.format(IntentCallbacks.DEFINED_CATEGORY, CategoryFixtures.EXISTING_CATEGORY.getId()))
            .option(CategoryFixtures.EXISTING_CATEGORY.getName()).build();;

    public static final HandledMessage.Keyboard.Option HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_CATEGORY = HandledMessage.Keyboard.Option.builder()
            .id(String.format(IntentCallbacks.DEFINED_CATEGORY, CategoryFixtures.ANOTHER_EXISTING_CATEGORY.getId()))
            .option(CategoryFixtures.ANOTHER_EXISTING_CATEGORY.getName()).build();;;
}
