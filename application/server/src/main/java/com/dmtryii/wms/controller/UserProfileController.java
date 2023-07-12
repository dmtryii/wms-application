package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.AddressRequest;
import com.dmtryii.wms.dto.request.ContactsUpdateRequest;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class UserProfileController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<User> getUserInfo(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/contacts")
    public ResponseEntity<User> updateContacts(@RequestBody ContactsUpdateRequest contactsUpdateRequest,
                                               Principal principal) {
        User user = userService.updateUserContacts(principal.getName(), contactsUpdateRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/address/city/{city_id}")
    public ResponseEntity<User> updateAddress(@PathVariable(name = "city_id") Long cityId,
                                              @RequestBody AddressRequest addressUpdate,
                                              Principal principal) {
        User user = userService.updateUserAddress(principal.getName(), cityId, addressUpdate);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
