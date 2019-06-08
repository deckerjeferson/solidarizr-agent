package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.messageHandler.HandledMessage;

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
        String message = String.format("<b>Nome:</b> %s\n"+
                "<b>Descrição:</b> %s\n",
                event.getName(),
                event.getDescription());

        if(!event.getEmail().isEmpty()){
            message += String.format("<b>Email:</b> %s\n", event.getEmail());
        }

        if(!event.getSite().isEmpty()){
            message += String.format("<b>Site:</b> %s\n", event.getSite());
        }

        if(!event.getPhone().isEmpty()){
            message += String.format("<b>Telefone:</b> %s\n", event.getPhone());
        }

       message += String.format("\n<b>Categoria:</b> %s\n" +
                        "<b>Público Alvo:</b> %s",
                event.getCategory().getName(),
                event.getTargetAudience().getName());

        return message;

    }
}
