package org.solidarizr.agent.messageHandler.intent.transformers;

import org.junit.Test;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.utils.CategoryFixtures;
import org.solidarizr.agent.utils.KeyboardOptionFixtures;
import org.solidarizr.agent.utils.TargetAudienceFixtures;

import java.lang.annotation.Target;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class KeyboardOptionTransformerTest {

    @Test
    public void from_list_of_target_audience_to_list_of_options() {
        List<TargetAudience> targetAudiences = List.of(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE,
                TargetAudienceFixtures.ANOTHER_EXISTING_TARGET_AUDIENCE);

        List<HandledMessage.Keyboard.Option> expected = List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_TARGET_AUDIENCE,
                KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_TARGET_AUDIENCE);

        List<HandledMessage.Keyboard.Option> result = KeyboardOptionTransformer.fromTargetAudienceList(targetAudiences);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void from_target_audience_to_option() {
        HandledMessage.Keyboard.Option result = KeyboardOptionTransformer.fromTargetAudience(TargetAudienceFixtures.EXISTING_TARGET_AUDIENCE);

        assertThat(result).isEqualTo(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_TARGET_AUDIENCE);
    }

    @Test
    public void from_category(){
        HandledMessage.Keyboard.Option result = KeyboardOptionTransformer.fromCategory(CategoryFixtures.EXISTING_CATEGORY);

        assertThat(result).isEqualTo(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_CATEGORY);
    }

    @Test
    public void from_category_list(){
        List<HandledMessage.Keyboard.Option> result = KeyboardOptionTransformer.fromCategoryList(List.of(CategoryFixtures.EXISTING_CATEGORY,
                CategoryFixtures.ANOTHER_EXISTING_CATEGORY));

        assertThat(result).isEqualTo(List.of(KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_EXISTING_CATEGORY,
                KeyboardOptionFixtures.HANDLED_MESSAGE_FROM_ANOTHER_EXISTING_CATEGORY));

    }
}