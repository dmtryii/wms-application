package com.dmtryii.wms.service;

import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not fount by id")
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(String.format("User not found on given username: %s", username))
        );
    }
}
