package ru.itis.hotel.service;

import java.util.UUID;

public interface AdminService {

    void toBanUser(UUID userId);

    void removeReview(UUID reviewId);
}
