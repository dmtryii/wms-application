package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.UserDTO;
import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.ContactsUpdateRequest;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class UserProfileController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @GetMapping
    public ResponseEntity<User> getUserInfo(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/contacts")
    public ResponseEntity<UserDTO> updateContacts(@RequestBody @Valid ContactsUpdateRequest contactsUpdateRequest,
                                                  Principal principal,
                                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        User user = userService.updateUserContacts(principal.getName(), contactsUpdateRequest);
        return new ResponseEntity<>(
                mapUser(user),
                HttpStatus.OK
        );
    }

    @PatchMapping("/address")
    public ResponseEntity<UserDTO> updateAddress(@RequestBody @Valid AddressUpdateRequest request,
                                                 Principal principal,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        User user = userService.updateUserAddress(principal.getName(), request);
        return new ResponseEntity<>(
                mapUser(user),
                HttpStatus.OK
        );
    }

    private UserDTO mapUser(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
