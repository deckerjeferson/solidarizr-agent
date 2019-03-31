package org.solidarizr.agent.messageHandler.intent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IntentDiscover {

    List<String> possibleGreetings = Arrays.asList("Oi!");

    public Intent discoverIntent(String message) {
        Intent intent;

        if(possibleGreetings.contains(message)){
            intent = Intent.GREETING;
        } else {
            intent = Intent.UNKNOWN;
        }

        return intent;
    }
}
