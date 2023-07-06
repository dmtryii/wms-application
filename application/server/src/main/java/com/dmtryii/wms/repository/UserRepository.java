package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByContactsEmail(String email);
    Optional<User> findByUsername(String username);
}
