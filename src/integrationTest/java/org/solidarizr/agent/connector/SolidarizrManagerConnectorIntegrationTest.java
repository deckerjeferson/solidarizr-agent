package org.solidarizr.agent.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class SolidarizrManagerConnectorIntegrationTest {

    @Autowired
    SolidarizrManagerConnector solidarizrManagerConnector;

    @Test
    public void get_all_target_audiences(){
        List<TargetAudience> result = solidarizrManagerConnector.targetAudienceResource().getAll();

        assertThat(result).isNotNull();
    }
}
