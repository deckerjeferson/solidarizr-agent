package org.solidarizr.agent.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    Integer id;

    String last_intent;

    Date last_interaction;

}
