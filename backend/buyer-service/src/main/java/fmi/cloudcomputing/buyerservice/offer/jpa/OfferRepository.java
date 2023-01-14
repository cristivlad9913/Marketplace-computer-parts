package fmi.cloudcomputing.buyerservice.offer.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
//    Daca pui `_` practi zici `cauta-mi in obj nested `buyer` care dupa field-ul `id`,
//    Adica `getBuyer().getId()`
    List<Offer> findAllByBuyer_Id(Long buyerId);
    List<Offer> findAllByPostSummary_IdOrPostSummary_OwnerId(Long postId, Long ownerId);
}
