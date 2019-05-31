package org.solidarizr.agent.chat.repository;

import org.solidarizr.agent.chat.repository.model.Chat;
import org.solidarizr.agent.chat.repository.model.Interaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends CrudRepository<Interaction, Integer> {
    List<Interaction> findByChatAndClosed(Chat chat, Boolean closed);
}
