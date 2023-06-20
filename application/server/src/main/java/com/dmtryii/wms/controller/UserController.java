package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.City;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.CityRepository;
import com.dmtryii.wms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {

        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "user_id") Long userId,
                                           @RequestBody User userRequest) {

        User user = userRepository.findById(userId).orElseThrow();
        City city = cityRepository.findById(userRequest.getCity().getId()).orElseThrow();

        user.setCity(city);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
}
