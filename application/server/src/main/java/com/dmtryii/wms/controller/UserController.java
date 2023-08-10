package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.UserDTO;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "user_id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(
                mapUser(user),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = userService.getAllUser()
                .stream()
                .map(this::mapUser)
                .toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private UserDTO mapUser(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
