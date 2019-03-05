package com.stackroute.userauthenticationservice.repository;

import com.stackroute.userauthenticationservice.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;




    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByEmail(String username);
        Boolean existsByEmail(String email);
    }

