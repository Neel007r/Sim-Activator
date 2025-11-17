package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.entities.SimCard;
import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import au.com.telstra.simcardactivator.model.SimObject;
import au.com.telstra.simcardactivator.repositories.SimCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SimCardServiceImpl implements SimCardService{

    @Autowired
    private SimCardRepo simCardRepo;

    @Override
    public Map<String, Object> getSimRecord(long id) {

        SimCard fetched = simCardRepo.findById(id).orElse(null);

        if(fetched ==null){
            return Map.of("Error","Sim card not found for ID: "+ id);
        }
        else {

            return Map.of("iccid",fetched.getIccid(),
                    "customerEmail",fetched.getCustomerEmail(),
                    "active",fetched.isActive()
            );
        }
    }

    @Override
    public void saveSimRecord(SimObject object, SimActuatorResponse response) {
        SimCard simCard = new SimCard(object, response);
        simCardRepo.save(simCard);
    }

}


