package org.solidarizr.agent.chat.repository.model;

import lombok.*;
import org.solidarizr.agent.messageHandler.intent.Intent;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Chat {

    @Id
    Long id;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Interaction> interactions;
}
