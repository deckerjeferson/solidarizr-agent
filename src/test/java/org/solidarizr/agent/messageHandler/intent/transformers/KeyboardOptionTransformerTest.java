package org.solidarizr.agent.messageHandler.intent.transformers;

import org.junit.Test;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;

import java.lang.annotation.Target;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class KeyboardOptionTransformerTest {

    @Test
    public void from_list_of_target_audience_to_list_of_options() {
        TargetAudience targetAudience1 = TargetAudience.builder()
                .id(1)
                .name("Target Audience 1").build();

        TargetAudience targetAudience2 = TargetAudience.builder()
                .id(2)
                .name("Target Audience 2").build();

        List<TargetAudience> targetAudiences = List.of(targetAudience1, targetAudience2);

        List<HandledMessage.Keyboard.Option> expected = List.of(HandledMessage.Keyboard.Option.builder().id("1").option("Target Audience 1").build(),
                HandledMessage.Keyboard.Option.builder().id("2").option("Target Audience 2").build());

        List<HandledMessage.Keyboard.Option> result = KeyboardOptionTransformer.fromTargetAudienceList(targetAudiences);

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void from_target_audience_to_option() {
        TargetAudience targetAudience = TargetAudience.builder()
                .id(1)
                .name("Target Audience 1").build();

        HandledMessage.Keyboard.Option expected = HandledMessage.Keyboard.Option.builder()
                .id("1")
                .option("Target Audience 1").build();

        HandledMessage.Keyboard.Option result = KeyboardOptionTransformer.fromTargetAudience(targetAudience);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void from_category(){
        Category category = Category.builder()
                .id(1)
                .name("Category 1").build();

        HandledMessage.Keyboard.Option expected = HandledMessage.Keyboard.Option.builder()
                .id("1")
                .option("Category 1").build();

        HandledMessage.Keyboard.Option result = KeyboardOptionTransformer.fromCategory(category);

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void from_category_list(){
        Category category1 = Category.builder()
                .id(1)
                .name("Category 1").build();

        Category category2 = Category.builder()
                .id(2)
                .name("Category 2").build();

        HandledMessage.Keyboard.Option expectedOption1 = HandledMessage.Keyboard.Option.builder()
                .id("1")
                .option("Category 1").build();

        HandledMessage.Keyboard.Option expectedOption2 = HandledMessage.Keyboard.Option.builder()
                .id("2")
                .option("Category 2").build();

        List<HandledMessage.Keyboard.Option> result = KeyboardOptionTransformer.fromCategoryList(List.of(category1, category2));

        assertThat(result).isEqualTo(List.of(expectedOption1, expectedOption2));

    }
}