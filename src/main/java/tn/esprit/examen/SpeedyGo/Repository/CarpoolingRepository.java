package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;
import tn.esprit.examen.SpeedyGo.entities.Services;

import java.util.List;

@Repository
public interface CarpoolingRepository extends MongoRepository<Carpooling, String> {
    List<Carpooling> findByPickupLocationAndDropoffLocation(String pickup, String dropoff);
    List<Carpooling> findByTypeservice(Services typeservice);
    List<Carpooling> findByIsRentalTrue();
    List<Carpooling> findByTotalRentalPriceGreaterThan(double price);

    List<Carpooling> findByDriverId(String driverId);


}
