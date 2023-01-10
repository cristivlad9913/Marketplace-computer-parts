package ownerapp.restservice;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class OfferRepository {
    public List<Offer> findAll() {
        return null;
    }

    public Offer findById(Long offerId) {
        return null;
    }

    public Offer save(Offer offer) {
        return offer;
    }
}
