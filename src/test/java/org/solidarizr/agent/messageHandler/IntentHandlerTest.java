package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.service.InteractionService;
import org.solidarizr.agent.connector.SolidarizrManagerConnector;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.intent.*;
import org.solidarizr.agent.utils.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IntentHandlerTest {

    IntentHandler intentHandler;

    @Mock
    SolidarizrManagerConnector solidarizrManagerConnector;

    @Mock
    InteractionService interactionService;


    @Before
    public void setUp(){
        intentHandler = new IntentHandler(solidarizrManagerConnector, interactionService);
    }

    @Test
    public void respond_greetings_message_when_receive_greetings_intent(){
        HandledMessage greetingsHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.GREETING, ChatFixture.CHAT_WITH_JUST_ID.getId()).get(0);

        assertThat(greetingsHandledMessage.getText()).isEqualTo(Messages.Responses.START);
        assertThat(greetingsHandledMessage.getKeyboard().getOptions()).containsExactly(StaticOptions.YES.getOption(), StaticOptions.NO.getOption());
    }

    @Test
    public void respond_start_message_when_receive_start_intent(){
        HandledMessage startHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.START, ChatFixture.CHAT_WITH_JUST_ID.getId()).get(0);

        assertThat(startHandledMessage.getText()).isEqualTo(Messages.Responses.START);
        assertThat(startHandledMessage.getKeyboard().getOptions()).containsExactly(StaticOptions.YES.getOption(), StaticOptions.NO.getOption());
    }

    @Test
    public void respond_create_projet_message_when_receive_create_projet_intent(){
        HandledMessage greetingsHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.CREATE_PROJECTS, ChatFixture.CHAT_WITH_JUST_ID.getId()).get(0);

        assertThat(greetingsHandledMessage.getText()).isEqualTo(Messages.Responses.CREATE_PROJECTS);
        assertThat(greetingsHandledMessage.getKeyboard()).isNull();
    }


    @Test
    public void respond_ask_target_audience_message_with_all_target_audiences_when_receive_ask_target_audience_intent_without_category_defined(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_CATEGORY.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITHOUT_CATEGORY));

        List<TargetAudience> targetAudiences = List.of(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE,
                TargetAudienceFixtures.ANOTHER_EXISTING_TARGET_AUDIENCE);

        when(solidarizrManagerConnector.getAllTargetAudiences()).thenReturn(targetAudiences);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_TARGET_AUDIENCE,
                KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_TARGET_AUDIENCE);

        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_TARGET_AUDIENCE, ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_CATEGORY.getId()).get(0);

        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Messages.Responses.ASK_TARGET_AUDIENCE);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(2);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
        verify(solidarizrManagerConnector, never()).findTargetAudiencesByEventsWithCategoryId(any(Integer.class));
    }

    @Test
    public void respond_ask_target_audience_message_with_filtered_by_category_target_audiences_when_receive_ask_target_audience_intent_category_defined(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED));

        List<TargetAudience> targetAudiences = List.of(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE);

        when(solidarizrManagerConnector.findTargetAudiencesByEventsWithCategoryId(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getCategory()))
                .thenReturn(targetAudiences);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_TARGET_AUDIENCE);

        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_TARGET_AUDIENCE, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()).get(0);

        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Messages.Responses.ASK_TARGET_AUDIENCE);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(1);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
        verify(solidarizrManagerConnector, never()).getAllTargetAudiences();
    }

    @Test
    public void respond_ask_categories_message_with_all_categories_when_recebei_ask_category_audience_intent_without_target_audience(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_TARGET_AUDIENCE.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITHOUT_TARGET_AUDIENCE));

        List<Category> categories = List.of(CategoryFixtures.EXISTING_CATEGORY,
                CategoryFixtures.ANOTHER_EXISTING_CATEGORY);

        when(solidarizrManagerConnector.getAllCategories()).thenReturn(categories);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_CATEGORY,
                KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_CATEGORY);


        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_CATEGORIES, ChatFixture.CHAT_WITH_OPEN_INTERACTION_WITHOUT_TARGET_AUDIENCE.getId()).get(0);

        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Messages.Responses.ASK_CATEGORY);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(2);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
        verify(solidarizrManagerConnector, never()).findCategoryByEventsWithTargetAudienceId(any(Integer.class));
    }

    @Test
    public void respond_ask_categories_message_with_filtered_categories_when_recebei_ask_category_audience_intent_with_target_audience(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED));

        List<Category> categories = List.of(CategoryFixtures.EXISTING_CATEGORY);

        when(solidarizrManagerConnector.findCategoryByEventsWithTargetAudienceId(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getTargetAudience())).thenReturn(categories);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_CATEGORY);


        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_CATEGORIES, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()).get(0);

        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Messages.Responses.ASK_CATEGORY);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(1);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
        verify(solidarizrManagerConnector, never()).getAllCategories();
    }



    @Test
    public void respond_not_understood_message_when_unknown_intent(){
        HandledMessage unknownHandledMessaged = intentHandler.getResponseBasedOnIntent(Intent.UNKNOWN, ChatFixture.CHAT_WITH_JUST_ID.getId()).get(0);
        assertThat(unknownHandledMessaged.getText()).isEqualTo(Messages.Responses.UNKNOWN);
    }

    @Test
    public void respond_events_when_get_evets_intent_and_there_is_events_to_this_category_and_target_audience(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED));

        List<Event> events = List.of(EventFixture.EVENT_WITH_ALL_INFORMATIONS_FILLED);

        when(solidarizrManagerConnector.getEventsBasedOnCategoryAndTargetAudience(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getCategory(),
                InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getTargetAudience()))
                .thenReturn(events);

        List<HandledMessage> expectedHandleMessages = List.of(HandledMessageFixture.FIXED_EVENT_RESPONSE_HANDLE_MESSAGE_WHEN_HAVE_EVENTS,
                HandledMessageFixture.HANDLED_MESSAGE_FROM_EVENT_WITH_ALL_INFORMATIONS_FILLED);

        List<HandledMessage> result = intentHandler.getResponseBasedOnIntent(Intent.GET_EVENTS, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId());

        assertThat(result).isEqualTo(expectedHandleMessages);
    }

    @Test
    public void respond_no_events_when_get_evets_intent_and_there_is_events_to_this_category_and_target_audience(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.of(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED));

        List<Event> events = Collections.emptyList();

        when(solidarizrManagerConnector.getEventsBasedOnCategoryAndTargetAudience(InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getCategory(),
                InteractionFixture.OPEN_INTERACTION_FROM_DB_WITH_ALL_INFORMATIONS_FILLED.getTargetAudience()))
                .thenReturn(events);

        List<HandledMessage> expectedHandleMessages = List.of(HandledMessageFixture.FIXED_EVENT_RESPONSE_HANDLE_MESSAGE_WHEN_HAVE_NOT_EVENTS);

        List<HandledMessage> result = intentHandler.getResponseBasedOnIntent(Intent.GET_EVENTS, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId());

        assertThat(result).isEqualTo(expectedHandleMessages);
    }

    @Test(expected = UnsupportedIntent.class)
    public void respont_unsupportedIntent_exception_when_not_supported_intent(){
        intentHandler.getResponseBasedOnIntent(null, ChatFixture.CHAT_WITH_JUST_ID.getId());
    }

    @Test(expected = NoInterractionFound.class)
    public void throw_NoInteractionFound_when_do_not_find_interaction_and_is_ask_categories_intent(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.empty());

        intentHandler.getResponseBasedOnIntent(Intent.ASK_CATEGORIES, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId());
    }

    @Test(expected = NoInterractionFound.class)
    public void throw_NoInteractionFound_when_do_not_find_interaction_and_is_ask_target_audience_intent(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.empty());

        intentHandler.getResponseBasedOnIntent(Intent.ASK_TARGET_AUDIENCE, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId());
    }

    @Test(expected = NoInterractionFound.class)
    public void throw_NoInteractionFound_when_do_not_find_interaction_and_is_get_events_audience_intent(){
        when(interactionService.getOpenInteractionFromChat(ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId()))
                .thenReturn(Optional.empty());

        intentHandler.getResponseBasedOnIntent(Intent.GET_EVENTS, ChatFixture.CHAT_WITH_OPEN_INTERACTION_ALL_INFORMATIONS_FILLED.getId());
    }
}