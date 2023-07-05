package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.UserDTO;
import com.dmtryii.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
