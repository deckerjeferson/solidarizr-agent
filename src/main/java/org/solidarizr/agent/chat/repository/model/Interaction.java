package org.solidarizr.agent.chat.repository.model;

import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Wither
@Builder
public class Interaction {

    public static final String SEQUENCE_NAME = "interaction_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name= SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    private Integer id;
    private Integer category;
    private Integer targetAudience;
    private Boolean closed;
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "chat")
    private Chat chat;


    public Boolean isTargetAudienceFilled(){
        return targetAudience != null;
    }

    public Boolean isCategoryFilled(){
        return category != null;
    }
}
