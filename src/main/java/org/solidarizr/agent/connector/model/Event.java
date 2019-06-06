package org.solidarizr.agent.connector.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {

    private Integer id;

    private String name;

    private String description;

    private Category category;

    private TargetAudience targetAudience;
}
