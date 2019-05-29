package org.solidarizr.agent.messageHandler.intent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IntentDiscover {

    public Intent discoverIntent(String message) {
        Intent intent;

        if(Intent.GREETING.getMessage().contains(message)){
            intent = Intent.GREETING;
        } else if (Intent.START.getMessage().equals(message)){
            intent = Intent.START;
        } else {
            intent = Intent.UNKNOWN;
        }

        return intent;
    }
}
