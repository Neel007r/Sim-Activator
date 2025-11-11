package au.com.telstra.simcardactivator.repositories;

import au.com.telstra.simcardactivator.entities.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimCardRepo  extends JpaRepository<SimCard, Long> {


}
