package ru.itis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hotel.model.FileEntity;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
