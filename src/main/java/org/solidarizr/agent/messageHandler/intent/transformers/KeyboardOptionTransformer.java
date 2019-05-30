package org.solidarizr.agent.messageHandler.intent.transformers;

import org.solidarizr.agent.connector.model.Category;
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

        targetAudiences.stream().forEach(targetAudience -> convertedOptions.add(fromTargetAudience(targetAudience)));

        return convertedOptions;
    }

    public static HandledMessage.Keyboard.Option fromCategory(Category category){
        return HandledMessage.Keyboard.Option.builder()
                .id(category.getId().toString())
                .option(category.getName()).build();
    }

    public static List<HandledMessage.Keyboard.Option> fromCategoryList(List<Category> categories) {
        List<HandledMessage.Keyboard.Option> convertedOptions = new ArrayList<>();

        categories.stream().forEach(category -> convertedOptions.add(fromCategory(category)));

        return convertedOptions;
    }
}
