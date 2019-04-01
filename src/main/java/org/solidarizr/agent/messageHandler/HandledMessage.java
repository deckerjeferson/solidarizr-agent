package org.solidarizr.agent.messageHandler;

import lombok.*;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode
public class HandledMessage {
    private String text;
    private Keyboard keyboard;

    @Value
    @Builder
    public static class Keyboard{

        @Singular
        private List<String> options;
    }
}
