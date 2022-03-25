package ru.itis.repository;

import ru.itis.models.File;

import java.util.Optional;

public interface FilesRepository {
    void saveFiles(File file);

    Optional<File> findByName(String name);
}
