package org.solidarizr.agent.utils;

import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.Messages;

public class HandledMessageFixture {
    public static final HandledMessage FIXED_EVENT_RESPONSE_HANDLE_MESSAGE_WHEN_HAVE_EVENTS = HandledMessage.builder()
            .text("Muito obrigado por utilizar o Solidarizr!\nAbaixo serão enviados os eventos encontrados! :)")
            .firstOrUnique(Boolean.TRUE).build();

    public static final HandledMessage FIXED_EVENT_RESPONSE_HANDLE_MESSAGE_WHEN_HAVE_NOT_EVENTS = HandledMessage.builder()
            .text("Não encontramos projetos para os dados que informaste :/")
            .firstOrUnique(Boolean.TRUE).build();

    public static final HandledMessage HANDLED_MESSAGE_FROM_EVENT_WITH_ALL_INFORMATIONS_FILLED = HandledMessage.builder()
            .text(String.format(Messages.EventText.NAME +
                            Messages.EventText.DESCRIPTION +
                            Messages.EventText.EMAIL +
                            Messages.EventText.SITE +
                            Messages.EventText.PHONE +
                            Messages.EventText.CATEGORY +
                            Messages.EventText.TARGET_AUDIENCE,
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getName(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getDescription(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getEmail(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getSite(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getPhone(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getCategory().getName(),
                    EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getTargetAudience().getName()))
            .firstOrUnique(Boolean.FALSE).build();

    public static final HandledMessage HANDLED_MESSAGE_FROM_EVENT_MISSING_EMAIL = HandledMessage.builder()
            .text(String.format(Messages.EventText.NAME +
                            Messages.EventText.DESCRIPTION +
                            Messages.EventText.SITE +
                            Messages.EventText.PHONE +
                            Messages.EventText.CATEGORY +
                            Messages.EventText.TARGET_AUDIENCE,
                    EventFixture.EVENT_MISSING_EMAIL.getName(),
                    EventFixture.EVENT_MISSING_EMAIL.getDescription(),
                    EventFixture.EVENT_MISSING_EMAIL.getSite(),
                    EventFixture.EVENT_MISSING_EMAIL.getPhone(),
                    EventFixture.EVENT_MISSING_EMAIL.getCategory().getName(),
                    EventFixture.EVENT_MISSING_EMAIL.getTargetAudience().getName()))
            .firstOrUnique(Boolean.FALSE).build();

    public static final HandledMessage HANDLED_MESSAGE_FROM_EVENT_MISSING_SITE = HandledMessage.builder()
            .text(String.format(Messages.EventText.NAME +
                            Messages.EventText.DESCRIPTION +
                            Messages.EventText.EMAIL +
                            Messages.EventText.PHONE +
                            Messages.EventText.CATEGORY +
                            Messages.EventText.TARGET_AUDIENCE,
                    EventFixture.EVENT_MISSING_SITE.getName(),
                    EventFixture.EVENT_MISSING_SITE.getDescription(),
                    EventFixture.EVENT_MISSING_SITE.getEmail(),
                    EventFixture.EVENT_MISSING_SITE.getPhone(),
                    EventFixture.EVENT_MISSING_SITE.getCategory().getName(),
                    EventFixture.EVENT_MISSING_SITE.getTargetAudience().getName()))
            .firstOrUnique(Boolean.FALSE).build();

    public static final HandledMessage HANDLED_MESSAGE_FROM_EVENT_MISSING_PHONE = HandledMessage.builder()
            .text(String.format(Messages.EventText.NAME +
                            Messages.EventText.DESCRIPTION +
                            Messages.EventText.EMAIL +
                            Messages.EventText.SITE +
                            Messages.EventText.CATEGORY +
                            Messages.EventText.TARGET_AUDIENCE,
                    EventFixture.EVENT_MISSING_PHONE.getName(),
                    EventFixture.EVENT_MISSING_PHONE.getDescription(),
                    EventFixture.EVENT_MISSING_PHONE.getEmail(),
                    EventFixture.EVENT_MISSING_PHONE.getSite(),
                    EventFixture.EVENT_MISSING_PHONE.getCategory().getName(),
                    EventFixture.EVENT_MISSING_PHONE.getTargetAudience().getName()))
            .firstOrUnique(Boolean.FALSE).build();
}
