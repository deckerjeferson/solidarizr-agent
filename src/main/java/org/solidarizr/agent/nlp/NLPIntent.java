package org.solidarizr.agent.nlp;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NLPIntent {

    private String reponse;
    private String intent;
}
