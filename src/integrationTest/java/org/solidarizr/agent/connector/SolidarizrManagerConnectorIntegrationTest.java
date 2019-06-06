package org.solidarizr.agent.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.solidarizr.agent.connector.model.Category;
import org.solidarizr.agent.connector.model.Event;
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
        List<TargetAudience> result = solidarizrManagerConnector.getAllTargetAudiences();

        assertThat(result).isNotNull();
    }

    @Test
    public void get_all_categories(){
        List<Category> result = solidarizrManagerConnector.getAllCategories();

        assertThat(result).isNotNull();
    }

    @Test
    public void get_events_by_category_and_target_audience(){
        List<Event> result = solidarizrManagerConnector.getEventsBasedOnCategoryAndTargetAudience(3, 3);

        assertThat(result).isNotNull();
    }

    @Test
    public void get_events_by_category_and_target_audience_with_not_Existing_filters(){
        List<Event> result = solidarizrManagerConnector.getEventsBasedOnCategoryAndTargetAudience(0,0);

        assertThat(result).isEmpty();
    }
}
