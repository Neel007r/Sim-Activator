package au.com.telstra.simcardactivator.model;

public class SimActuatorResponse {

    private boolean success;

    //will check whether response is true or false
    public boolean getSuccess(){
        return success;
    }

    //will set response of the actuator microservice by this method
    public void setSuccess(boolean success){
        this.success=success;
    }
}
