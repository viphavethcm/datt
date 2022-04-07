package com.nhatduy.at14.datt.repository;

import com.nhatduy.at14.datt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);

    @Query("FROM User u WHERE u.email = :email")
    Optional<User> findOneWithAuthoritiesByEmail(String email);

}