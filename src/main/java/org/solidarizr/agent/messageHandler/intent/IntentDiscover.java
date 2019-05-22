package org.solidarizr.agent.messageHandler.intent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IntentDiscover {

    public static final String START = "/start";
    List<String> possibleGreetings = Arrays.asList("Oi!");

    public Intent discoverIntent(String message) {
        Intent intent;

        if(possibleGreetings.contains(message)){
            intent = Intent.GREETING;
        } else if (START.equals(message)){
            intent = Intent.START;
        } else {
            intent = Intent.UNKNOWN;
        }

        return intent;
    }
}
