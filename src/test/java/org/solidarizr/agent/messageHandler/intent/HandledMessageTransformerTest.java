package org.solidarizr.agent.messageHandler.intent;

import org.junit.Test;
import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.Messages;
import org.solidarizr.agent.messageHandler.intent.transformers.HandledMessageTransformer;
import org.solidarizr.agent.utils.EventFixture;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandledMessageTransformerTest {

    @Test
    public void all_information_filled_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED
        );

        String textMessage = String.format(Messages.EventText.NAME +
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
                EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED.getTargetAudience().getName());

        List<HandledMessage> excepted = List.of(HandledMessage.builder()
            .text(textMessage)
            .firstOrUnique(Boolean.FALSE).build());


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_email_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_EMAIL
        );

        String textMessage = String.format(Messages.EventText.NAME +
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
                EventFixture.EVENT_MISSING_EMAIL.getTargetAudience().getName());

        List<HandledMessage> excepted = List.of(HandledMessage.builder()
                .text(textMessage)
                .firstOrUnique(Boolean.FALSE).build());


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_site_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_SITE
        );

        String textMessage = String.format(Messages.EventText.NAME +
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
                EventFixture.EVENT_MISSING_SITE.getTargetAudience().getName());

        List<HandledMessage> excepted = List.of(HandledMessage.builder()
                .text(textMessage)
                .firstOrUnique(Boolean.FALSE).build());


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_phone_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_PHONE
        );

        String textMessage = String.format(Messages.EventText.NAME +
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
                EventFixture.EVENT_MISSING_PHONE.getTargetAudience().getName());

        List<HandledMessage> excepted = List.of(HandledMessage.builder()
                .text(textMessage)
                .firstOrUnique(Boolean.FALSE).build());


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }
}