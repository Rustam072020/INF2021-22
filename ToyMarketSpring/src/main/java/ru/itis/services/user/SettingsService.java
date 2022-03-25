package ru.itis.services.user;

import ru.itis.dto.FilesForm;
import ru.itis.models.File;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface SettingsService {
    void upload(FilesForm form, InputStream fileInputStream);

    void updatePhoto(String email, String photo);

    boolean fileNameIsExist(String name);

    void download(String fileName, OutputStream outputStream);

    Optional<File> setFile(String name);

    boolean correctOldPassword(String email, String oldPassword);

    void updatePassword(String email, String newPassword);

    void dropCookie(HttpServletResponse response, String login);
}
