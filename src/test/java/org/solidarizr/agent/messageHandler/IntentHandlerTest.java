package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.solidarizr.agent.chat.service.InteractionService;
import org.solidarizr.agent.connector.SolidarizrManagerConnector;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentHandler;
import org.solidarizr.agent.messageHandler.intent.StaticOptions;
import org.solidarizr.agent.messageHandler.intent.UnsupportedIntent;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntentHandlerTest {

    public static final Long CHAT_ID = 1L;
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
        HandledMessage greetingsHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.GREETING, CHAT_ID).get(0);

        assertThat(greetingsHandledMessage.getText()).isEqualTo(Intent.GREETING.getResponse());
        assertThat(greetingsHandledMessage.getKeyboard().getOptions()).containsExactly(StaticOptions.YES.getOption(), StaticOptions.NO.getOption());
    }

    @Test
    public void respond_start_message_when_receive_start_intent(){
        HandledMessage startHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.START, CHAT_ID).get(0);

        assertThat(startHandledMessage.getText()).isEqualTo(Intent.START.getResponse());
        assertThat(startHandledMessage.getKeyboard().getOptions()).containsExactly(StaticOptions.YES.getOption(), StaticOptions.NO.getOption());
    }

    @Test
    public void respond_ask_target_audience_message_when_receive_ask_target_audience_intent(){
        TargetAudience targetAudienceToBeCoverted1 = TargetAudience.builder().id(1).name("Target Audience 1").build();
        TargetAudience targetAudienceToBeCoverted2 = TargetAudience.builder().id(2).name("Target Audience 2").build();
        List<TargetAudience> targetAudiences = List.of(targetAudienceToBeCoverted1, targetAudienceToBeCoverted2);

        when(solidarizrManagerConnector.getAllTargetAudiences()).thenReturn(targetAudiences);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(HandledMessage.Keyboard.Option.builder()
                        .id("defined_target_audience="+targetAudienceToBeCoverted1.getId().toString())
                        .option(targetAudienceToBeCoverted1.getName()).build(),
                HandledMessage.Keyboard.Option.builder()
                        .id("defined_target_audience="+targetAudienceToBeCoverted2.getId().toString())
                        .option(targetAudienceToBeCoverted2.getName()).build());

        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_TARGET_AUDIENCE, CHAT_ID).get(0);

        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Intent.ASK_TARGET_AUDIENCE.getResponse());
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(2);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
    }

    @Test
    public void respond_ask_categories_message_when_recebei_ask_category_audience_intent(){
        Category categoryToBeCoverted1 = Category.builder().id(1).name("Category 1").build();
        Category categoryToBeCoverted2 = Category.builder().id(2).name("Category 2").build();
        List<Category> categories = List.of(categoryToBeCoverted1, categoryToBeCoverted2);

        when(solidarizrManagerConnector.getAllCategories()).thenReturn(categories);

        List<HandledMessage.Keyboard.Option> expectedOptions = List.of(HandledMessage.Keyboard.Option.builder()
                        .id("defined_category="+categoryToBeCoverted1.getId().toString())
                        .option(categoryToBeCoverted1.getName()).build(),
                HandledMessage.Keyboard.Option.builder()
                        .id("defined_category="+categoryToBeCoverted2.getId().toString())
                        .option(categoryToBeCoverted2.getName()).build());


        HandledMessage askTargetAudienceHandledMessage = intentHandler.getResponseBasedOnIntent(Intent.ASK_CATEGORIES, CHAT_ID).get(0);
        assertThat(askTargetAudienceHandledMessage.getText()).isEqualTo(Intent.ASK_CATEGORIES.getResponse());
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions().size()).isEqualTo(2);
        assertThat(askTargetAudienceHandledMessage.getKeyboard().getOptions()).isEqualTo(expectedOptions);
    }

    @Test
    public void respond_not_understood_message_when_unknown_intent(){
        HandledMessage unknownHandledMessaged = intentHandler.getResponseBasedOnIntent(Intent.UNKNOWN, CHAT_ID).get(0);
        assertThat(unknownHandledMessaged.getText()).isEqualTo(Intent.UNKNOWN.getResponse());
    }

    @Test
    public void repospond_events_when_get_evets_intent(){

    }

    @Test(expected = UnsupportedIntent.class)
    public void respont_unsupportedIntent_exception_when_not_supported_intent(){
        intentHandler.getResponseBasedOnIntent(null, CHAT_ID);
    }
}