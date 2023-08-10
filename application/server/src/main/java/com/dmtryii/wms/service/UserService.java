package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.ContactsUpdateRequest;
import com.dmtryii.wms.exception.UserNotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Contacts;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final ContactsService contactsService;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not fount by id: " + userId)
        );
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User updateUserAddress(String username, AddressUpdateRequest request) {
        User user = getUserByUsername(username);
        Address address = getUserAddressByUsername(username);

        addressService.update(address.getId(), request);
        user.setAddress(address);

        return userRepository.save(user);
    }

    public User updateUserContacts(String username, ContactsUpdateRequest request) {
        User user = getUserByUsername(username);
        Contacts contacts = getUserContactsByUsername(username);

        contactsService.update(contacts.getId(), request);
        user.setContacts(contacts);

        return userRepository.save(user);
    }

    public Address getUserAddressByUsername(String username) {
        return getUserByUsername(username).getAddress();
    }

    public Contacts getUserContactsByUsername(String username) {
        return getUserByUsername(username).getContacts();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found by username: " + username)
        );
    }

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found: " + username)
        );
    }
}
