package org.solidarizr.agent.messageHandler.intent;

import org.junit.Test;
import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.Messages;
import org.solidarizr.agent.messageHandler.intent.transformers.HandledMessageTransformer;
import org.solidarizr.agent.utils.EventFixture;
import org.solidarizr.agent.utils.HandledMessageFixture;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandledMessageTransformerTest {

    @Test
    public void all_information_filled_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED
        );

        List<HandledMessage> excepted = List.of(HandledMessageFixture.HANDLED_MESSAGE_FROM_EVENT_WITH_ALL_INFORMATIONS_FILLED);


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_email_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_EMAIL
        );

        List<HandledMessage> excepted = List.of(HandledMessageFixture.HANDLED_MESSAGE_FROM_EVENT_MISSING_EMAIL);


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_site_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_SITE
        );

        List<HandledMessage> excepted = List.of(HandledMessageFixture.HANDLED_MESSAGE_FROM_EVENT_MISSING_SITE);


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }

    @Test
    public void missing_phone_events_transform(){
        List<Event> eventsToTransform = List.of(
                EventFixture.EVENT_MISSING_PHONE
        );

        List<HandledMessage> excepted = List.of(HandledMessageFixture.HANDLED_MESSAGE_FROM_EVENT_MISSING_PHONE);


        List<HandledMessage> transformedHandledMessage = HandledMessageTransformer.from(eventsToTransform);

        assertThat(transformedHandledMessage).isEqualTo(excepted);

    }
}