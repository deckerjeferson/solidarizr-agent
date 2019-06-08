package org.solidarizr.agent.messageHandler.intent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.solidarizr.agent.messageHandler.HandledMessage;

@AllArgsConstructor
@Getter
public enum StaticOptions {
    YES(HandledMessage.Keyboard.Option.builder()
            .id("want_to_find_project")
            .option("Sim!").build()),
    NO(HandledMessage.Keyboard.Option.builder()
            .id("want_to_create_project")
            .option("Não! Gostaria de cadastrar um projeto voluntário!").build());

    private HandledMessage.Keyboard.Option option;


}
