package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.chat.repository.model.Interaction;
import org.solidarizr.agent.chat.service.InteractionService;
import org.solidarizr.agent.connector.SolidarizrManagerConnector;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.Event;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.intent.transformers.HandledMessageTransformer;
import org.solidarizr.agent.messageHandler.intent.transformers.KeyboardOptionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.solidarizr.agent.messageHandler.intent.Intent.*;

@Component
public class IntentHandler {

    private SolidarizrManagerConnector solidarizrManagerConnector;
    private InteractionService interactionService;

    @Autowired
    public IntentHandler(SolidarizrManagerConnector solidarizrManagerConnector, InteractionService interactionService) {
        this.solidarizrManagerConnector = solidarizrManagerConnector;
        this.interactionService = interactionService;
    }

    public List<HandledMessage> getResponseBasedOnIntent(Intent intent, Long chatId) {
        List<HandledMessage> responseMessage = new ArrayList<>();

        if(intent == null){
            throw new UnsupportedIntent();
        }

        Interaction currentInteraction = getInteractionIfNeeded(intent, chatId);

        switch (intent){
            case START:
                responseMessage.add(getStartOrGreetingsHandledMessage(START));
                break;
            case GREETING:
                responseMessage.add(getStartOrGreetingsHandledMessage(GREETING));
                break;

            case ASK_TARGET_AUDIENCE:
                responseMessage.add(getAskTargetAudienceResponseMessage(currentInteraction));
                break;

            case ASK_CATEGORIES:
                responseMessage.add(getAskCategoriesResponseMessage(currentInteraction));
                break;

            case GET_EVENTS:
                responseMessage.addAll(getGetEventsResponseMessage(currentInteraction));
                break;

            case CREATE_PROJECTS:
                return getCreateProjectsResponse();

            case UNKNOWN:
                responseMessage.add(getUnknownResponseMessage());
                break;

            default:
                throw new UnsupportedIntent(intent);
        }

        return responseMessage;
    }

    private Interaction getInteractionIfNeeded(Intent intent, Long chatId) {
        Interaction interaction = null;

        if(ASK_CATEGORIES.equals(intent) || ASK_TARGET_AUDIENCE.equals(intent) || GET_EVENTS.equals(intent)){
            Optional<Interaction> interactionFromDB = interactionService.getOpenInteractionFromChat(chatId);

            if(interactionFromDB.isPresent()){
                interaction = interactionFromDB.get();
            } else {
                throw new NoInterractionFound();
            }
        }
        return interaction;
    }

    private List<HandledMessage> getCreateProjectsResponse() {
        return List.of(HandledMessage.builder()
                .text(CREATE_PROJECTS.getResponse())
                .firstOrUnique(Boolean.TRUE)
                .build());
    }

    private List<HandledMessage> getGetEventsResponseMessage(Interaction currentInteraction) {
        List<HandledMessage> responseMessages = new ArrayList<>();

            List<Event> events = solidarizrManagerConnector.getEventsBasedOnCategoryAndTargetAudience(currentInteraction.getCategory()
                    , currentInteraction.getTargetAudience());

            if(events.size() > 0) {
                responseMessages.add(HandledMessage.builder().text("Muito obrigado por utilizar o Solidarizr!\nAbaixo serão enviados os eventos encontrados! :)")
                        .firstOrUnique(Boolean.TRUE).build());
                responseMessages.addAll(HandledMessageTransformer.from(events));
            } else {
                responseMessages.add(HandledMessage.builder().text("Não encontramos projetos para os dados que informaste :/")
                        .firstOrUnique(Boolean.TRUE).build());
            }

            interactionService.save(currentInteraction.withClosed(true));


        return responseMessages;
    }

    private HandledMessage getUnknownResponseMessage() {
        HandledMessage responseMessage;
        responseMessage = HandledMessage.builder()
                .text(UNKNOWN.getResponse())
                .build();
        return responseMessage;
    }

    private HandledMessage getAskCategoriesResponseMessage(Interaction currentInteraction) {
        HandledMessage responseMessage;
        List<Category> categories;

        if(currentInteraction.isTargetAudienceFilled()){
            categories = solidarizrManagerConnector.findCategoryByEventsWithTargetAudienceId(currentInteraction.getTargetAudience());
        } else {
            categories = solidarizrManagerConnector.getAllCategories();
        }

        List<HandledMessage.Keyboard.Option> categoriesOption = KeyboardOptionTransformer.fromCategoryList(categories);

        responseMessage = HandledMessage.builder()
                .text(ASK_CATEGORIES.getResponse())
                .keyboard(HandledMessage.Keyboard.builder()
                        .options(categoriesOption)
                        .build())
                .firstOrUnique(Boolean.TRUE)
                .build();

        return responseMessage;
    }

    private HandledMessage getAskTargetAudienceResponseMessage(Interaction currentInteraction) {
        HandledMessage responseMessage;
        List<TargetAudience> targetAudienceList;

        if(currentInteraction.isCategoryFilled()){
            targetAudienceList = solidarizrManagerConnector.findTargetAudiencesByEventsWithCategoryId(currentInteraction.getCategory());
        } else {
            targetAudienceList = solidarizrManagerConnector.getAllTargetAudiences();
        }

        List<HandledMessage.Keyboard.Option> targetAudienceOptions = KeyboardOptionTransformer.fromTargetAudienceList(targetAudienceList);

        responseMessage = HandledMessage.builder()
                .text(ASK_TARGET_AUDIENCE.getResponse())
                .keyboard(HandledMessage.Keyboard.builder()
                        .options(targetAudienceOptions)
                        .build())
                .firstOrUnique(Boolean.TRUE)
                .build();

        return responseMessage;
    }

    private HandledMessage getStartOrGreetingsHandledMessage(Intent start) {
        HandledMessage responseMessage;
        responseMessage = HandledMessage.builder()
                .text(start.getResponse())
                .keyboard(HandledMessage.Keyboard.builder()
                        .option(StaticOptions.YES.getOption())
                        .option(StaticOptions.NO.getOption())
                        .build())
                .firstOrUnique(Boolean.TRUE)
                .build();

        return responseMessage;
    }
}
