package org.solidarizr.agent.messageHandler;

import org.junit.Before;
import org.junit.Test;
import org.solidarizr.agent.messageHandler.intent.Intent;
import org.solidarizr.agent.messageHandler.intent.IntentDiscover;

import static org.assertj.core.api.Assertions.assertThat;

public class IntentDiscoverTest {

    IntentDiscover intentDiscover;

    @Before
    public void setUp(){
        intentDiscover = new IntentDiscover();
    }

    @Test
    public void find_greetings_intent_from_greetings_message(){
        assertThat(intentDiscover.discoverIntent("Oi!")).isEqualTo(Intent.GREETING);
    }

    @Test
    public void find_start_intent_from_start_message(){
        assertThat(intentDiscover.discoverIntent("/start")).isEqualTo(Intent.START);
    }

    @Test
    public void return_unknownIntent_from_unidentifiable_message(){
        assertThat(intentDiscover.discoverIntent("ASDLKASJDLASJDÇLASJDASJ")).isEqualTo(Intent.UNKNOWN);
    }

}