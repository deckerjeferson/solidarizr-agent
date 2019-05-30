package org.solidarizr.agent.messageHandler.intent.transformers;

import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;

import java.util.ArrayList;
import java.util.List;

public class KeyboardOptionTransformer {
    public static HandledMessage.Keyboard.Option fromTargetAudience(TargetAudience targetAudience){
        return HandledMessage.Keyboard.Option.builder()
                .id(targetAudience.getId().toString())
                .option(targetAudience.getName()).build();
    }

    public static List<HandledMessage.Keyboard.Option> fromTargetAudienceList(List<TargetAudience> targetAudiences){
        List<HandledMessage.Keyboard.Option> convertedOptions = new ArrayList<>();

        for(TargetAudience targetAudience : targetAudiences){
            convertedOptions.add(fromTargetAudience(targetAudience));
        }
        //targetAudiences.stream().forEach(targetAudience -> ));

        return convertedOptions;
    }

}
