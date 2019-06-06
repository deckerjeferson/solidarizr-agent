package org.solidarizr.agent.chat.service;

import org.solidarizr.agent.chat.repository.InteractionRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {

    InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public Interaction createInteractionFromChat(Chat chat){
        closeChatOpenInteractions(chat);

        Interaction newInteraction = Interaction.builder()
                .chat(chat)
                .closed(Boolean.FALSE).build();

        return interactionRepository.save(newInteraction);

    }

    private void closeChatOpenInteractions(Chat chat) {
        List<Interaction> openedInteractions = interactionRepository.findByChatAndClosed(chat, false);

        openedInteractions.forEach(openInteraction -> {
            Interaction toCloseInteraction = Interaction.builder()
                    .chat(openInteraction.getChat())
                    .id(openInteraction.getId())
                    .category(openInteraction.getCategory())
                    .targetAudience(openInteraction.getTargetAudience())
                    .closed(Boolean.TRUE).build();

            interactionRepository.save(toCloseInteraction);
        });
    }

    public Optional<Interaction> getOpenInteractionFromChat(Chat chat){
        return interactionRepository.findByChatAndClosed(chat, false).stream().findFirst();
    }

    public Interaction   save(Interaction interaction) {
        return interactionRepository.save(interaction);
    }
}
