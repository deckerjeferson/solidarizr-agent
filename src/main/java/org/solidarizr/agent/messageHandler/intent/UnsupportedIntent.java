package org.solidarizr.agent.messageHandler.intent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnsupportedIntent extends RuntimeException {

    public UnsupportedIntent(Intent intent) {
        super(String.format("Unsupported Intent: %s", intent));
    }
}
