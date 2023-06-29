package com.dmtryii.wms.service;

import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not fount by id")
        );
    }
}
