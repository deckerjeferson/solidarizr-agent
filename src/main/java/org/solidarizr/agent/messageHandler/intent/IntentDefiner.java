package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;
import org.solidarizr.agent.chat.service.ChatService;
import org.solidarizr.agent.chat.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntentDefiner {

    ChatService chatService;
    InteractionService interactionService;

    @Autowired
    public IntentDefiner(ChatService chatService, InteractionService interactionService) {
        this.chatService = chatService;
        this.interactionService = interactionService;
    }

    public Intent defineIntent(String input, Long chatId) {
        Intent intent;

        if(isGreetings(input)){
            intent = Intent.GREETING;
        } else if (isStartCommand(input)){
            intent = Intent.START;
        } else if (isWantToFindAProject(input)){
            Chat newChat = chatService.saveChat(chatId);
            Interaction newInteraction = interactionService.createInteractionFromChat(newChat);

            intent = getIntentBasedOnFilledInformations(newInteraction);
        } else if (isSomeFillableInformationDefined(input)) {
            Chat currentChat = chatService.find(chatId).get();
            Interaction openInteraction = interactionService.getOpenInteractionsFromChat(currentChat).get();
            Interaction filledInteraction = fillInteractionBasedOnInformationFilled(input, openInteraction);

            Interaction savedInteraction = interactionService.save(filledInteraction);

            intent = getIntentBasedOnFilledInformations(savedInteraction);
        } else {
            intent = Intent.UNKNOWN;
        }

        return intent;
    }

    private Interaction fillInteractionBasedOnInformationFilled(String input, Interaction interaction) {
        Interaction filledIntearction;

        if(input.contains("category")){
            filledIntearction = interaction.withCategory(extractId(input));
        } else {
            filledIntearction = interaction.withTargetAudience(extractId(input));
        }

        return filledIntearction;
    }

    private Integer extractId(String input) {
        return Integer.valueOf(input.substring(input.indexOf("=")+1));
    }

    private Boolean isSomeFillableInformationDefined(String input) {
        return input.contains("defined") && input.contains("=");
    }

    private Intent getIntentBasedOnFilledInformations(Interaction newInteraction) {
        Intent intent;

        if(!newInteraction.isTargetAudienceFilled()){
            intent = Intent.ASK_TARGET_AUDIENCE;
        } else if(!newInteraction.isCategoryFilled()) {
            intent = Intent.ASK_CATEGORIES;
        } else {
            intent = Intent.GET_EVENTS;
        }

        return intent;
    }

    private Boolean isStartCommand(String input) {
        return Intent.START.getTrigger().equals(input);
    }

    private Boolean isGreetings(String input){
        return Intent.GREETING.getTrigger().equals(input);
    }

    private Boolean isWantToFindAProject(String input) { return Intent.WANT_TO_FIND_PROJECT.getTrigger().equals(input); }
}
