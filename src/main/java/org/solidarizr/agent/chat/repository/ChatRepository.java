package org.solidarizr.agent.chat.repository;

import org.solidarizr.agent.chat.repository.model.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    public Optional<Chat> findById(Integer id);
}
