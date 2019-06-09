package org.solidarizr.agent.messageHandler;

public class Messages {

    public static class EventText {
        public static final String NAME = "<b>Nome:</b> %s\n";
        public static final String DESCRIPTION = "<b>Descrição:</b> %s\n";
        public static final String EMAIL = "<b>Email:</b> %s\n";
        public static final String SITE = "<b>Site:</b> %s\n";
        public static final String PHONE = "<b>Telefone:</b> %s\n";
        public static final String CATEGORY = "\n<b>Categoria:</b> %s\n";
        public static final String TARGET_AUDIENCE = "<b>Público Alvo:</b> %s\n";
    }

    public static class IntentCallbacks {
        public static final String DEFINDED_TARGET_AUDIENCE = "defined_target_audience=%s";
        public static final String DEFINED_CATEGORY = "defined_category=%s";
    }
}
