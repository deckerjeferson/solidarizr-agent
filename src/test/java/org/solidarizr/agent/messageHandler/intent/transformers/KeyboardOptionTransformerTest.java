package org.solidarizr.agent.messageHandler.intent.transformers;

import org.junit.Test;
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
}