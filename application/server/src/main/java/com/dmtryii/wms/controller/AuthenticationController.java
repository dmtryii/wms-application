package com.dmtryii.wms.controller;

import com.dmtryii.wms.auth.AuthenticationRequest;
import com.dmtryii.wms.auth.AuthenticationResponse;
import com.dmtryii.wms.auth.RegisterRequest;
import com.dmtryii.wms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.authenticate(request));
    }

}
