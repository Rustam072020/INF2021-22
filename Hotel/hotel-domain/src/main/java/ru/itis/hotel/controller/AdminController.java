package ru.itis.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotel.api.AdminApi;
import ru.itis.hotel.service.AdminService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public void toBanUser(UUID userId) {
        adminService.toBanUser(userId);
    }

    @Override
    public void removeReview(UUID reviewId) {
        adminService.removeReview(reviewId);
    }
}
