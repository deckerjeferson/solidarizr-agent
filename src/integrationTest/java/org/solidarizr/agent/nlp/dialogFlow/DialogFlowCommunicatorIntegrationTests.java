package org.solidarizr.agent.nlp.dialogFlow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.nlp.NLPIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "local")
@SpringBootTest
public class DialogFlowCommunicatorIntegrationTests {

    private final List<String> POSSIBLE_WELCOME_MESSAGES = Arrays.asList("Olá!", "Oi!");


    @Autowired
    private DialogFlowConfigs configs;

    private DialogFlowCommunicator communicator;

    @Before
    public void setUp(){
        communicator = new DialogFlowCommunicator(configs);
    }

    @Test
    public void should_get_inputWelcome_as_intent_and_welcome_message_from_Oi_input(){
        NLPIntent intent = communicator.detectIntent("Oi");

        assertThat(intent.getIntent()).isEqualTo("input.welcome");
        assertThat(intent.getReponse()).isIn(POSSIBLE_WELCOME_MESSAGES);
    }
}
