package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.Messages;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandledMessageTransformer {
    public static List<HandledMessage> from(List<Event> events) {
        List<HandledMessage> responses = new ArrayList<>();

        events.forEach(event -> {
            responses.add(HandledMessage.builder()
                    .text(fillEventMessageText(event))
                    .firstOrUnique(false)
                    .build());
        });

        return responses;
    }

    private static String fillEventMessageText(Event event) {
        String message = String.format(Messages.EventText.NAME +
                Messages.EventText.DESCRIPTION,
                event.getName(),
                event.getDescription());

        if(!StringUtils.isEmpty(event.getEmail())){
            message += String.format(Messages.EventText.EMAIL, event.getEmail());
        }

        if(!StringUtils.isEmpty(event.getSite())){
            message += String.format(Messages.EventText.SITE, event.getSite());
        }

        if(!StringUtils.isEmpty(event.getPhone())){
            message += String.format(Messages.EventText.PHONE, event.getPhone());
        }

        message += String.format(Messages.EventText.CATEGORY +
                        Messages.EventText.TARGET_AUDIENCE,
                event.getCategory().getName(),
                event.getTargetAudience().getName());

        return message;

    }
}
