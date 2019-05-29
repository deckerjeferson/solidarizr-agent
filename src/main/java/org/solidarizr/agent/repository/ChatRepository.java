package org.solidarizr.agent.repository;

import org.solidarizr.agent.repository.model.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Integer> {

    public Optional<Chat> findById(Integer id);
}
