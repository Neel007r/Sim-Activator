package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.entities.SimCard;
import au.com.telstra.simcardactivator.repositories.SimCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimCardServiceImpl implements SimCardService{

    @Autowired
    private SimCardRepo simCardRepo;

    @Override
    public Optional<SimCard> getSimRecord(long id) {
        return simCardRepo.findById(id);
    }

    @Override
    public void saveSimRecord(SimCard simCard) {
        SimCard save = simCardRepo.save(simCard);
    }
}
