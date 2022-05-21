package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.request.ReviewRequest;
import ru.itis.hotel.dto.response.ApartmentResponse;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.exception.apartment.ApartmentNotFoundException;
import ru.itis.hotel.exception.reservation.ReservationForbiddenException;
import ru.itis.hotel.exception.review.ReviewAlreadyExistException;
import ru.itis.hotel.mapper.ApartmentMapper;
import ru.itis.hotel.mapper.ReviewMapper;
import ru.itis.hotel.model.ApartmentEntity;
import ru.itis.hotel.model.ReviewEntity;
import ru.itis.hotel.repository.ApartmentRepository;
import ru.itis.hotel.repository.ReservationRepository;
import ru.itis.hotel.repository.ReviewRepository;
import ru.itis.hotel.service.ApartmentService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ReviewRepository reviewRepository;
    private final ApartmentMapper apartmentMapper;
    private final ReviewMapper reviewMapper;

    @Transactional
    @Override
    public UUID setApartmentReview(UUID apartmentId, ReviewRequest request) {

        UserResponse user = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApartmentEntity apartment = apartmentRepository.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

        if (reviewRepository.findByClientAndApartments(user.getUuid(), apartment).isPresent()) {
            throw new ReviewAlreadyExistException();
        }

        apartment.setRating((apartment.getRating()*apartment.getClientAmount()+request.getRating())/
                            (apartment.getClientAmount()+1)
        );
        apartment.setClientAmount(apartment.getClientAmount()+1);

        ReviewEntity review = reviewMapper.toReviewEntity(request);
        review.setApartments(apartment);
        review.setClient(user.getUuid());
        return reviewRepository.save(review).getUuid();
    }

    @Override
    public ApartmentResponse getApartment(UUID apartmentId) {
        return apartmentMapper.toApartmentResponse(apartmentRepository.findById(apartmentId)
                .orElseThrow(ApartmentNotFoundException::new));
    }
}
