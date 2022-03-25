package ru.itis.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.dto.FilesForm;
import ru.itis.models.File;
import ru.itis.models.User;
import ru.itis.repository.FilesRepository;
import ru.itis.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final static String PAGE_PERSON_COOKIE_NAME = "pagePerson";

    private final static int DROP_COOKIE_AGE = 0;

    @Value("${storage.path}")
    private String storagePath;

    private FilesRepository filesRepository;
    private UserRepository userRepository;

    @Autowired
    public SettingsServiceImpl(FilesRepository filesRepository, UserRepository userRepository) {
        this.filesRepository = filesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void upload(FilesForm form, InputStream fileInputStream) {
        try {
            Files.copy(fileInputStream, Paths.get(storagePath + form.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        File file = File.builder()
                .fileName(form.getFileName())
                .fileMimeType(form.getFileMimeType())
                .fileSize(form.getFileSize())
                .contentDisposition(form.getContentDisposition())
                .build();
        filesRepository.saveFiles(file);
    }

    @Override
    public void updatePhoto(String email, String photo) {
        userRepository.updatePhoto(email, photo);
    }

    @Override
    public boolean fileNameIsExist(String name){
        return filesRepository.findByName(name).isPresent();
    }

    @Override
    public void download(String fileName, OutputStream outputStream) {
        try {
            Files.copy(Paths.get(storagePath + fileName), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<File> setFile(String name){
        return filesRepository.findByName(name);
    }

    @Override
    public boolean correctOldPassword(String email, String oldPassword){
        Optional<User> user = userRepository.findPassByEmail(email);
        if (user.get().getPassword().equals(oldPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(String email, String newPassword){
        userRepository.updatePass(email, newPassword);
    }

    @Override
    public void dropCookie(HttpServletResponse response, String login) {
        Cookie cookie = new Cookie(PAGE_PERSON_COOKIE_NAME, login);
        cookie.setMaxAge(DROP_COOKIE_AGE);
        response.addCookie(cookie);
    }
}
