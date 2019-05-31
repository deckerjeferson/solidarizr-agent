package org.solidarizr.agent.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.chat.repository.ChatRepository;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.chat.repository.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "local")
public class ChatRepositoryIntegrationTest {

    @Autowired
    ChatRepository repository;

    Chat entity = Chat.builder().id(12345L)
            .build();

    @After
    public void deleteInsertedEntity(){
        Optional<Chat> entityToDelete = repository.findById(entity.getId());

        if(entityToDelete.isPresent()){
            repository.delete(entityToDelete.get());
        }
    }

    @Test
    public void save_chat() throws Exception{
        repository.save(entity);
        assertThat(repository.findById(entity.getId()).isPresent()).isTrue();
    }

    @Test
    public void find_chat_by_id() throws Exception{
        repository.save(entity);
        assertThat(repository.findById(entity.getId()).isPresent()).isTrue();
    }

    @Test
    public void delete_chat() throws Exception{
        repository.save(entity);
        Chat entityToDelete = repository.findById(entity.getId()).get();

        repository.delete(entityToDelete);
    }
}
