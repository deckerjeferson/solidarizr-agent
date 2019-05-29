package org.solidarizr.agent.repository;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.repository.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
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

    Chat entity = Chat.builder().id(12345)
            .last_intent(Intent.START.name())
            .last_interaction(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
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
