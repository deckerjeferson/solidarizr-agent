package org.solidarizr.agent.connector;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.solidarizr.agent.connector.model.TargetAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

    public List<TargetAudience> getAllTargetAudiences(){
        ResponseEntity<List<TargetAudience>> result = restTemplate.exchange(URL + "/targetAudiences",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TargetAudience>>() {});

        return result.getBody();
    }
}
