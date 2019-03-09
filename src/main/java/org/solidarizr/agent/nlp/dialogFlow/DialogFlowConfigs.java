package org.solidarizr.agent.nlp.dialogFlow;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "dialogflow")
public class DialogFlowConfigs {
    String projectName;
    String languageCode;
    String credentialsPath;
}
