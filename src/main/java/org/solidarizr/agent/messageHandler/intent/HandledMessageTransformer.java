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
                    .text(String.format("<b>Nome:</b> %s\n"+
                                        "<b>Descrição:</b> %s\n"+
                                        "<b>Telefone:</b> %s\n"+
                                        "<b>Site:</b> %s\n"+
                                        "<b>Email:</b> %s\n\n"+
                                        "<b>Categoria:</b> %s\n" +
                                        "<b>Público Alvo:</b> %s",
                            event.getName(),
                            event.getDescription(),
                            event.getPhone(),
                            event.getSite(),
                            event.getEmail(),
                            event.getCategory().getName(),
                            event.getTargetAudience().getName())).build());
        });

        return responses;
    }
}
