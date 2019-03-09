package org.solidarizr.agent.nlp;

public interface NLPCommunicator {

    NLPIntent detectIntent(String text);
}
