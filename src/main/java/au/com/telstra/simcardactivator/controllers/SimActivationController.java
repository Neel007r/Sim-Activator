package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.model.SimActivationRequest;
import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class SimActivationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimActivationRequest request){
        try {
            HashMap<String, String> payload = new HashMap<>();
            payload.put("iccid", request.getIccid());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<HashMap<String, String>> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<SimActuatorResponse> response = restTemplate.postForEntity("http://localhost:8444/actuate",
                    entity, SimActuatorResponse.class);

            System.out.println("Response from actuator: " + response.getBody().isSuccess());

            if (response.getBody() != null && response.getBody().isSuccess()) {
                System.out.println("Activation successful for ICCID: " + request.getIccid());
                System.out.println("Activation successful customer email: " + request.getCustomerEmail());
                return ResponseEntity.ok(request.getCustomerEmail());
            } else {
                System.out.println("Activation failed for ICCID: " + request.getIccid());
                System.out.println("Activation failed customer email: " + request.getCustomerEmail());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request.getCustomerEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while activating SIM");
        }
    }


}
