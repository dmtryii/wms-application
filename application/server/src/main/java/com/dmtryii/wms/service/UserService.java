package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressRequest;
import com.dmtryii.wms.dto.request.ContactsUpdateRequest;
import com.dmtryii.wms.dto.UserDTO;
import com.dmtryii.wms.dto_mapper.UserDTOMapper;
import com.dmtryii.wms.exception.UserNotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Contacts;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.AddressRepository;
import com.dmtryii.wms.repository.ContactsRepository;
import com.dmtryii.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ContactsRepository contactsRepository;
    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final UserDTOMapper userDTOMapper;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not fount by id: " + userId)
        );
    }

    public User updateUserAddress(String username, Long cityId, AddressRequest addressUpdate) {
        User user = getUserByUsername(username);
        Address address = getUserAddressByUsername(username);

        address.setCurrentName(addressUpdate.currentAddressName());
        address.setCity(cityService.getCityById(cityId));
        addressRepository.save(address);

        user.setAddress(address);

        return userRepository.save(user);
    }

    public User updateUserContacts(String username, ContactsUpdateRequest contactsUpdateRequest) {
        User user = getUserByUsername(username);
        Contacts contacts = getUserContactsByUsername(username);

        contacts.setPhone(contactsUpdateRequest.phone());
        contacts.setFirstName(contactsUpdateRequest.firstName());
        contacts.setLastName(contactsUpdateRequest.lastName());
        contacts.setSocialNetworks(contactsUpdateRequest.socialNetworks());
        contactsRepository.save(contacts);

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

    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found: " + username)
        );
    }
}
