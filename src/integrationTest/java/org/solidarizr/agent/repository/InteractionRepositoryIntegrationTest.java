package org.solidarizr.agent.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.chat.repository.ChatRepository;
import org.solidarizr.agent.chat.repository.InteractionRepository;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class InteractionRepositoryIntegrationTest {

    @Autowired
    InteractionRepository repository;

    @Autowired
    ChatRepository chatRepository;

    @After
    public void clean_chats_and_its_interactions(){
        Optional<Chat> chat = chatRepository.findById(1L);

        if(chat.isPresent()){
            chatRepository.delete(chat.get());
        }
    }

    @Test
    public void save_interaction_without_category_targetaudience_not_closed(){
        Chat chat = Chat.builder().id(1L).build();
        chatRepository.save(chat);

        Interaction interaction = Interaction.builder().chat(chat).closed(Boolean.FALSE).build();
        Interaction savedInteraction = repository.save(interaction);

        assertThat(savedInteraction.getId()).isNotNull();
        assertThat(savedInteraction.getCategory()).isNull();
        assertThat(savedInteraction.getTargetAudience()).isNull();
        assertThat(savedInteraction.getClosed()).isFalse();
        assertThat(savedInteraction.getChat()).isEqualTo(chat);
    }

    @Test
    public void save_interaction_with_category_targetaudience_closed(){
        Chat chat = Chat.builder().id(1L).build();
        chatRepository.save(chat);

        Interaction interaction = Interaction.builder()
                .chat(chat)
                .category(1)
                .targetAudience(1)
                .closed(Boolean.TRUE).build();
        Interaction savedInteraction = repository.save(interaction);

        assertThat(savedInteraction.getId()).isNotNull();
        assertThat(savedInteraction.getCategory()).isEqualTo(1);
        assertThat(savedInteraction.getTargetAudience()).isEqualTo(1);
        assertThat(savedInteraction.getClosed()).isTrue();
        assertThat(savedInteraction.getChat()).isEqualTo(chat);
    }

    @Test
    public void update_from_interaction_with_category_targetaudience_closed_to_interaction_with_category_targetaudience_closed(){
        Chat chat = Chat.builder().id(1L).build();
        chatRepository.save(chat);

        Interaction interactionWithoutNothing = Interaction.builder().chat(chat).closed(Boolean.FALSE).build();
        Integer interactionId = repository.save(interactionWithoutNothing).getId();

        Interaction interaction = Interaction.builder()
                .id(interactionId)
                .chat(chat)
                .category(1)
                .targetAudience(1)
                .closed(Boolean.TRUE).build();

        Interaction savedInteraction = repository.save(interaction);

        assertThat(savedInteraction.getId()).isNotNull();
        assertThat(savedInteraction.getCategory()).isEqualTo(1);
        assertThat(savedInteraction.getTargetAudience()).isEqualTo(1);
        assertThat(savedInteraction.getClosed()).isTrue();
        assertThat(savedInteraction.getChat().getId()).isEqualTo(1L);
    }

    @Test
    public void find_interactions_by_chat_and_closed_field(){
        Chat chat = Chat.builder().id(1L).build();
        Chat savedChat = chatRepository.save(chat);

        Interaction interaction = Interaction.builder().chat(chat).closed(Boolean.FALSE).build();
        repository.save(interaction).getId();

        List<Interaction> openInteractions = repository.findByChatAndClosed(savedChat, Boolean.FALSE);

        assertThat(openInteractions.size()).isEqualTo(1);
    }

}
