package fmi.cloudcomputing.buyerservice.offer.service;

import fmi.cloudcomputing.buyerservice.offer.presentation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfferService {

    List<OfferListingDto> getAllForCurrentUser();

    OfferDto getById(Long offerId);
    OfferDto create(CreateOfferDto dto);

    OfferDto update(long id, UpdateOfferDto dto);

    void delete(long id);

    List<OfferListingDto> getAll(OfferFilters filters);
}
