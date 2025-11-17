package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.model.SimActuatorResponse;
import au.com.telstra.simcardactivator.model.SimObject;

import java.util.Map;

public interface SimCardService {

    public Map<String, Object> getSimRecord(long id);

    public void saveSimRecord(SimObject object, SimActuatorResponse response);

}
