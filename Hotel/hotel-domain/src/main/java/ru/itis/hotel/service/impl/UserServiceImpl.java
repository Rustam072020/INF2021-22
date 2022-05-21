package ru.itis.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.hotel.dto.request.CreateUserRequest;
import ru.itis.hotel.dto.request.UserRequest;
import ru.itis.hotel.dto.response.UserResponse;
import ru.itis.hotel.enums.EmailStatus;
import ru.itis.hotel.enums.Role;
import ru.itis.hotel.enums.UserState;
import ru.itis.hotel.exception.user.UserNotFoundException;
import ru.itis.hotel.mapper.UserMapper;
import ru.itis.hotel.model.UserEntity;
import ru.itis.hotel.repository.UserRepository;
import ru.itis.hotel.service.UserService;
import ru.itis.hotel.util.EmailUtil;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${name.mail.template}")
    private String emailTemplaName;

    @Override
    public Optional<UserResponse> findBySubject(String subject) {
        return userRepository.findOneByEmail(subject)
                .map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        return userRepository.findOneByEmail(userRequest.getEmail())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getHashPassword()))
                .map(userMapper::toUserResponse)
                .orElseThrow();
    }

    @Override
    public UUID signUp(CreateUserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity newUser = userMapper.toUserEntity(user);
        newUser.setRole(user.getRole());
        newUser.setState(UserState.ACTIVE);
        newUser.setEmailStatus(EmailStatus.NOT_CONFIRMED);
        UUID id = userRepository.save(newUser).getUuid();
        toSendConfirmLetterEmail(newUser);
        return id;
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return userMapper.toUserResponse(
                userRepository.findById(userId)
                        .orElseThrow()
        );
    }

    @Transactional
    @Override
    public void confirmEmail(UUID userId){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setEmailStatus(EmailStatus.CONFIRMED);
    }

    //TODO Подумать как сделать обновление юзера
    @Transactional
    @Override
    public UserResponse updateUser(CreateUserRequest updateUser) {
        return null;
    }

    private void toSendConfirmLetterEmail(UserEntity newUser) {
        Runnable task = () -> {
            HashMap<String, String> data = new HashMap<>();
            data.put("confirm_code", newUser.getUuid().toString());
            data.put("name", newUser.getFullName());
            emailUtil.sendMail(newUser.getEmail(), "confirm", emailTemplaName,
                    data);
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
