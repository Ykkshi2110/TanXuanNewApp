package com.peter.tanxuannewapp.service;

import com.peter.tanxuannewapp.domain.Role;
import com.peter.tanxuannewapp.domain.User;
import com.peter.tanxuannewapp.domain.resposne.ResCreateUserDTO;
import com.peter.tanxuannewapp.exception.ResourceAlreadyExistsException;
import com.peter.tanxuannewapp.repository.RoleRepository;
import com.peter.tanxuannewapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String EMAIL_EXISTS_EXCEPTION_MESSAGE = "Email already exists";
    private final ModelMapper modelMapper;

    public ResCreateUserDTO handleCreateUser(User reqCreateUser) {
        if (this.userRepository.existsByEmail(reqCreateUser.getEmail()))
            throw new ResourceAlreadyExistsException(EMAIL_EXISTS_EXCEPTION_MESSAGE);
        reqCreateUser.setPassword(passwordEncoder.encode(reqCreateUser.getPassword()));

        // check role
        if (reqCreateUser.getRole() != null) {
            Role userRole = this.roleRepository
                    .findById(reqCreateUser
                            .getRole()
                            .getId())
                    .orElse(null);
            reqCreateUser.setRole(userRole);
        }
        return modelMapper.map(this.userRepository.save(reqCreateUser), ResCreateUserDTO.class);
    }

    public User handleGetUserByEmail(String email) {
        return this.userRepository
                .findUserByEmail(email)
                .orElse(null);
    }

    // set refreshToken in user
    public void setRefreshTokenInUserDB(String refreshToken, String email) {
        User currentUserDB = this.handleGetUserByEmail(email);
        if (currentUserDB != null) {
            currentUserDB.setRefreshToken(refreshToken);
            this.userRepository.save(currentUserDB);
        }
    }

    public User handleGetUserByRefreshTokenAndEmail(String refreshToken, String email) {
        return this.userRepository.findUserByRefreshTokenAndEmail(refreshToken, email).orElse(null);
    }

}
