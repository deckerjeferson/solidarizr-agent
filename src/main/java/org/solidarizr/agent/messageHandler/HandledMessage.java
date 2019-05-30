package org.solidarizr.agent.messageHandler;

import lombok.*;

import java.util.List;
import java.util.Optional;

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
        private List<Option> options;

        @Value
        @Builder
        public static class Option{
            private String id;
            private String option;
        }
    }
}
