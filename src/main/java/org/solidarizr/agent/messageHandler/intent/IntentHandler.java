package org.solidarizr.agent.messageHandler.intent;

import org.solidarizr.agent.connector.SolidarizrManagerConnector;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.intent.transformers.KeyboardOptionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.solidarizr.agent.messageHandler.intent.Intent.*;

@Component
public class IntentHandler {

    private SolidarizrManagerConnector solidarizrManagerConnector;

    @Autowired
    public IntentHandler(SolidarizrManagerConnector solidarizrManagerConnector) {
        this.solidarizrManagerConnector = solidarizrManagerConnector;
    }

    public HandledMessage getResponseBasedOnIntent(Intent intent) {
        HandledMessage responseMessage;

        if(intent == null){
            throw new UnsupportedIntent();
        }

        switch (intent){
            case START:
                responseMessage = HandledMessage.builder()
                        .text(START.getResponse())
                        .keyboard(HandledMessage.Keyboard.builder()
                                .option(StaticOptions.YES.getOption())
                                .option(StaticOptions.NO.getOption())
                                .build())
                        .build();
                break;
            case GREETING:
                responseMessage = HandledMessage.builder()
                        .text(GREETING.getResponse())
                        .keyboard(HandledMessage.Keyboard.builder()
                                .option(StaticOptions.YES.getOption())
                                .option(StaticOptions.NO.getOption())
                                .build())
                        .build();
                break;

            case ASK_TARGET_AUDIENCE:
                List<TargetAudience> targetAudienceList = solidarizrManagerConnector.getAllTargetAudiences();
                List<HandledMessage.Keyboard.Option> options = KeyboardOptionTransformer.fromTargetAudienceList(targetAudienceList);

                responseMessage = HandledMessage.builder()
                        .text(ASK_TARGET_AUDIENCE.getResponse())
                        .keyboard(HandledMessage.Keyboard.builder()
                                .options(options)
                                .build())
                        .build();
                break;

            case UNKNOWN:
                responseMessage = HandledMessage.builder()
                        .text(UNKNOWN.getResponse())
                        .build();
                break;

            default:
                throw new UnsupportedIntent(intent);
        }
        return responseMessage;
    }
}
