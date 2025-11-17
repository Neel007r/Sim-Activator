package au.com.telstra.simcardactivator.model;

import au.com.telstra.simcardactivator.entities.SimCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimObject {

    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimObject() {
    }

    public SimObject(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public SimObject(SimCard simCard) {
        this.iccid = simCard.getIccid();
        this.customerEmail=simCard.getCustomerEmail();
        this.active=simCard.isActive();
    }

    public String getIccid(){
        return iccid;
    }

    public void setIccid(String iccid){
        this.iccid=iccid;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail){
        this.customerEmail=customerEmail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
