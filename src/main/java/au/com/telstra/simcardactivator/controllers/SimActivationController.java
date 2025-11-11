package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.entities.SimCard;
import au.com.telstra.simcardactivator.model.SimActivationRequest;
import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SimActivationController {

    @Autowired
    private SimCardService simCardService;


    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimActivationRequest request) {
        ResponseEntity<SimActuatorResponse> response;
        try {
            HashMap<String, String> payload = new HashMap<>();
            payload.put("iccid", request.getIccid());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<HashMap<String, String>> entity = new HttpEntity<>(payload, headers);

            response = restTemplate.postForEntity("http://localhost:8444/actuate",
                    entity, SimActuatorResponse.class);

            System.out.println("Response from actuator: " + response.getBody().isSuccess());
            SimCard sim = new SimCard();
            sim.setIccid(request.getIccid());
            sim.setCustomerEmail(request.getCustomerEmail());
            sim.setActive(response.getBody().isSuccess());

            simCardService.saveSimRecord(sim);

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


    @GetMapping("/simcard/{id}")
    public Map<String,Object> getSim(@PathVariable long id){
        Optional<SimCard> simRecord = simCardService.getSimRecord(id);
        if(simRecord.isEmpty()){
            return Map.of("Error","Sim card not found for ID: "+ id);
        }
        else {
//            return ResponseEntity.ok(simRecord);
            SimCard result = simRecord.get();
            return Map.of("iccid",result.getIccid(),
                    "customerEmail",result.getCustomerEmail(),
                    "active",result.isActive()
            );
        }
    }


}
