package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import au.com.telstra.simcardactivator.model.SimObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Activator {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8444/actuate";

    public SimActuatorResponse activateSim(SimObject object) {
            return restTemplate.postForObject(URL, object, SimActuatorResponse.class);
    }


}
