package org.solidarizr.agent.messageHandler.intent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  Intent {
    START("/start"), GREETING("Oi!"), UNKNOWN;

    private String message;

}
