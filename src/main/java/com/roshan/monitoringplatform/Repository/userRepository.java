package com.roshan.monitoringplatform.Repository;

import com.roshan.monitoringplatform.Entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface userRepository extends JpaRepository<User , UUID> {

    boolean existsByEmail(String email);


    User findByEmail(@NotNull String email);
}
