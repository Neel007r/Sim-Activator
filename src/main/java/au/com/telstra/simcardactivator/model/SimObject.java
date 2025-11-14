package au.com.telstra.simcardactivator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimActivationRequest {

    private String iccid;
    private String customerEmail;
    private boolean active;

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
}
