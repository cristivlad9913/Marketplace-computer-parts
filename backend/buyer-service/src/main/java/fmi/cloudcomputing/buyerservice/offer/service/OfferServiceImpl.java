package fmi.cloudcomputing.buyerservice.offer.service;

import fmi.cloudcomputing.buyerservice.offer.jpa.Offer;
import fmi.cloudcomputing.buyerservice.offer.jpa.OfferRepository;
import fmi.cloudcomputing.buyerservice.offer.jpa.OfferStatus;
import fmi.cloudcomputing.buyerservice.offer.jpa.PostSummary;
import fmi.cloudcomputing.buyerservice.offer.presentation.*;
import fmi.cloudcomputing.buyerservice.post.PostDto;
import fmi.cloudcomputing.buyerservice.post.PostRestConsumer;
import fmi.cloudcomputing.buyerservice.user.jpa.User;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final PostRestConsumer postRestConsumer;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository,
                            PostRestConsumer postRestConsumer,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.postRestConsumer = postRestConsumer;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfferListingDto> getAllForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return offerRepository.findAllByBuyer_Id(user.getId())
                .stream()
                .map(offer -> {
                    OfferListingDto dto = modelMapper.map(offer, OfferListingDto.class);
                    dto.setPostId(offer.getPostSummary().getId());
                    dto.setPostTitle(offer.getPostSummary().getTitle());
                    dto.setRequestedPrice(offer.getPostSummary().getRequestPrice());
                    dto.setOwnerUsername(offer.getPostSummary().getOwnerUsername());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OfferDto getById(Long offerId) {
        return offerRepository.findById(offerId)
                .map(offer ->{
                    OfferDto dto = modelMapper.map(offer, OfferDto.class);
                    User buyer = offer.getBuyer();
                    dto.setBuyer(modelMapper.map(buyer, UserDto.class));
                    return dto;
                })
                .orElseThrow();
    }

    @Override
    public OfferDto create(CreateOfferDto dto) {
        PostDto postDto;
        try {
            postDto = postRestConsumer.getById(dto.getPostId()).getBody();
            if (postDto == null) {
                throw new NoSuchElementException(dto.getPostId().toString());
            }
        } catch (Exception e) {
            throw new NoSuchElementException(dto.getPostId().toString());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Offer offer = modelMapper.map(dto, Offer.class);
        offer.setBuyer(user);
        offer.setStatus(OfferStatus.PENDING);
        PostSummary summary = modelMapper.map(postDto, PostSummary.class);
        summary.setOwnerEmail(postDto.getOwner().getEmail());
        summary.setOwnerPhone(postDto.getOwner().getPhone());
        summary.setOwnerUsername(postDto.getOwner().getUsername());
        summary.setRequestPrice(postDto.getTotal());
        offer.setPostSummary(summary);

        offer = offerRepository.save(offer);
        OfferDto result = modelMapper.map(offer, OfferDto.class);
        result.setBuyer(modelMapper.map(user, UserDto.class));

        return result;
    }


    @Override
    public OfferDto update(long id, UpdateOfferDto dto) {
        Offer offer = offerRepository.findById(id).orElseThrow();
        offer.setOfferedPrice(dto.getOfferedPrice());
        if(dto.getStatus() != null) {
            offer.setStatus(dto.getStatus());
        }
        offer = offerRepository.save(offer);

        User user = Hibernate.unproxy(offer.getBuyer(), User.class);
        OfferDto result = modelMapper.map(offer, OfferDto.class);
        result.setBuyer(modelMapper.map(user, UserDto.class));

        return result;
    }

    @Override
    public PostOfferDto updateStatusInternal(long id, UpdateOfferDto dto) {
        Offer offer = offerRepository.findById(id).orElseThrow();
        if(dto.getStatus() != null) {
            offer.setStatus(dto.getStatus());
        }
        offer = offerRepository.save(offer);
        return makePostOffer(offer);
    }

    @Override
    public void delete(long id) {
        offerRepository.findById(id)
                .ifPresentOrElse(
                        offerRepository::delete,
                        () -> {
                            throw new NoSuchElementException(String.valueOf(id));
                        });
    }

    @Override
    public List<PostOfferDto> getAll(OfferFilters filters) {
        //                    Foloseste model mapper, ca e mai rapid decat sa pui de mana setChestie();
        return offerRepository.findAllByPostSummary_Id(filters.getPostId())
                .stream()
                .map(this::makePostOffer)
                .collect(Collectors.toList());
    }

    private PostOfferDto makePostOffer(Offer offer) {
        PostOfferDto dto = modelMapper.map(offer, PostOfferDto.class);
        User buyer = Hibernate.unproxy(offer.getBuyer(), User.class);
        dto.setOfferId(offer.getId());
        dto.setBuyer(modelMapper.map(buyer, UserDto.class));
        dto.setOfferedPrice(offer.getOfferedPrice());
        dto.setStatus(offer.getStatus());
        return dto;
    }
}
