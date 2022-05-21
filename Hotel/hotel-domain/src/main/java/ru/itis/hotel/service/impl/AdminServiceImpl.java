package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.hotel.enums.UserState;
import ru.itis.hotel.exception.user.UserNotFoundException;
import ru.itis.hotel.repository.ReviewRepository;
import ru.itis.hotel.repository.UserRepository;
import ru.itis.hotel.service.AdminService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void toBanUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new)
                .setState(UserState.BANED);
    }

    @Override
    public void removeReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
