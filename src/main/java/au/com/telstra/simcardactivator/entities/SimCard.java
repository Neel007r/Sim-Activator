package au.com.telstra.simcardactivator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimCard() {
    }

    public SimCard(String iccid, String customerEmail, boolean success) {
    }



    public long getId() {
        return id;
    }

    public String getIccid() {
        return iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isActive() {
        return active;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
