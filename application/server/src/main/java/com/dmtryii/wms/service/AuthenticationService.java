package com.dmtryii.wms.service;

import com.dmtryii.wms.auth.AuthenticationRequest;
import com.dmtryii.wms.auth.AuthenticationResponse;
import com.dmtryii.wms.auth.RegisterRequest;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Contacts;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.AddressRepository;
import com.dmtryii.wms.repository.ContactsRepository;
import com.dmtryii.wms.repository.UserRepository;
import com.dmtryii.wms.security.JwtService;
import com.dmtryii.wms.validation.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ContactsRepository contactsRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterValidator registerValidator;

    public AuthenticationResponse register(RegisterRequest request) {

        registerValidator.validate(request);

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .contacts(
                        contactsRepository.save(new Contacts(request.getEmail()))
                )
                .address(
                        addressRepository.save(new Address())
                )
                .roles(Collections.singleton(ERole.CUSTOMER))
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
