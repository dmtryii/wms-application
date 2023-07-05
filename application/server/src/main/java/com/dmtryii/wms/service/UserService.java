package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.UserDTO;
import com.dmtryii.wms.dto_mapper.UserDTOMapper;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not fount by id: " + userId)
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(String.format("User not found on given username: %s", username))
        );
    }

    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }
}
