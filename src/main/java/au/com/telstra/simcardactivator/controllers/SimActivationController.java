package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.entities.SimCard;
import au.com.telstra.simcardactivator.model.SimObject;
import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SimActivationController {

    @Autowired
    private SimCardService simCardService;

    @Autowired
    private Activator activator;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimObject request) {
        SimActuatorResponse response = activator.activateSim(request);

        simCardService.saveSimRecord(request, response);

        if(response.getSuccess()){
            return ResponseEntity.ok("Sim Activated successfully for iccid: " + request.getIccid());
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sim Activation Failed for iccid: " + request.getIccid());
        }
    }


    @GetMapping("/simcard/{id}")
    public Map<String, Object> getSim(@PathVariable long id){
        return simCardService.getSimRecord(id);

    }


}
