package org.solidarizr.agent.messageHandler.intent.transformers;

import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.intent.IntentTriggers;

import java.util.ArrayList;
import java.util.List;

public class KeyboardOptionTransformer {
    public static HandledMessage.Keyboard.Option fromTargetAudience(TargetAudience targetAudience){
        return HandledMessage.Keyboard.Option.builder()
                .id(String.format(IntentTriggers.DEFINDED_TARGET_AUDIENCE, targetAudience.getId()))
                .option(targetAudience.getName()).build();
    }

    public static List<HandledMessage.Keyboard.Option> fromTargetAudienceList(List<TargetAudience> targetAudiences){
        List<HandledMessage.Keyboard.Option> convertedOptions = new ArrayList<>();

        targetAudiences.stream().forEach(targetAudience -> convertedOptions.add(fromTargetAudience(targetAudience)));

        return convertedOptions;
    }

    public static HandledMessage.Keyboard.Option fromCategory(Category category){
        return HandledMessage.Keyboard.Option.builder()
                .id(String.format(IntentTriggers.DEFINED_CATEGORY, category.getId()))
                .option(String.format(category.getName())).build();
    }

    public static List<HandledMessage.Keyboard.Option> fromCategoryList(List<Category> categories) {
        List<HandledMessage.Keyboard.Option> convertedOptions = new ArrayList<>();

        categories.stream().forEach(category -> convertedOptions.add(fromCategory(category)));

        return convertedOptions;
    }
}
