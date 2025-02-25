package com.peter.tanxuannewapp.repository;

import com.peter.tanxuannewapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByRefreshTokenAndEmail(String refreshToken, String email);
}
