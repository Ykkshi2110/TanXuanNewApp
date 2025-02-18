package com.peter.tanxuannewapp.service;

import com.peter.tanxuannewapp.domain.User;
import com.peter.tanxuannewapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User handleCreateUser(User reqCreateUser) {
        reqCreateUser.setPassword(passwordEncoder.encode(reqCreateUser.getPassword()));
        return userRepository.save(reqCreateUser);
    }

}
