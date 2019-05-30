package org.solidarizr.agent.messageHandler.intent;

import org.springframework.stereotype.Component;

@Component
public class IntentDiscover {

    public Intent discoverIntent(String message) {
        Intent intent;

        if(Intent.GREETING.getTrigger().contains(message)){
            intent = Intent.GREETING;
        } else if (Intent.START.getTrigger().equals(message)){
            intent = Intent.START;
        } else if (Intent.ASK_TARGET_AUDIENCE.getTrigger().equals(message)){
            intent = Intent.ASK_TARGET_AUDIENCE;
        }else {
            intent = Intent.UNKNOWN;
        }

        return intent;
    }
}
