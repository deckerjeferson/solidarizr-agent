package org.solidarizr.agent.nlp.dialogFlow;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.solidarizr.agent.nlp.NLPCommunicator;
import org.solidarizr.agent.nlp.NLPIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class DialogFlowCommunicator implements NLPCommunicator {

    @Autowired
    public DialogFlowCommunicator(DialogFlowConfigs configs) {
        this.configs = configs;
        this.initialize();
    }

    private DialogFlowConfigs configs;
    private SessionsClient sessionsClient;
    private SessionName session;

    private void initialize() {
        try{
            this.sessionsClient = createSessionClientBasedOnCredentialsFile(configs.credentialsPath);

            session = SessionName.of(configs.getProjectName(), UUID.randomUUID().toString());
        } catch (IOException ex){
            String errorMessage = "Can't Initialize DialogFlow Session Client";

            log.error(errorMessage, ex);
            throw new RuntimeException(errorMessage);
        }
    }

    @NotNull
    private SessionsClient createSessionClientBasedOnCredentialsFile(String credentialsFilePath) throws IOException {
        SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(new FileInputStream(credentialsFilePath))))
                .build();

        return SessionsClient.create(sessionsSettings);
    }

    @Override
    public NLPIntent detectIntent(String message) {
        TextInput.Builder textInput = TextInput.newBuilder().setText(message).setLanguageCode(configs.getLanguageCode());
        QueryInput queryInput = QueryInput.newBuilder()
                .setText(textInput)
                .build();

        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
        QueryResult queryResult = response.getQueryResult();

        return NLPIntent.builder()
                .intent(queryResult.getAction())
                .reponse(queryResult.getFulfillmentText())
                .build();
    }
}
