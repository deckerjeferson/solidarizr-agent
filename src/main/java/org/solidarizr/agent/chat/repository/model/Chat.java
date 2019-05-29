package org.solidarizr.agent.chat.repository.model;

import lombok.*;
import org.solidarizr.agent.messageHandler.intent.Intent;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    Long id;

    Intent last_intent;

    Date last_interaction;

}
