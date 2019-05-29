package org.solidarizr.agent.connector;

import lombok.extern.slf4j.Slf4j;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class SolidarizrManagerConnector {
    final static String URL = "https://solidarizr-manager.herokuapp.com";

    RestTemplate restTemplate;

    public SolidarizrManagerConnector() {
        this.restTemplate = new RestTemplate();
    }

    public TargetAudienceResource targetAudienceResource(){
        return new TargetAudienceResource(restTemplate);
    }

    static class TargetAudienceResource {
        RestTemplate restTemplate;

        public TargetAudienceResource(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public List<TargetAudience> getAll(){
            List<TargetAudience> result = restTemplate.getForObject(URL+"/targetAudiences", List.class);
            return result;
        }
    }
}
