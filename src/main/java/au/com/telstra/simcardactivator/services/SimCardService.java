package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.entities.SimCard;

import java.util.Optional;

public interface SimCardService {

    public Optional<SimCard> getSimRecord(long id);

    public void saveSimRecord(SimCard simCard);
}
