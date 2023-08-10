package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.ContactsUpdateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Contacts;
import com.dmtryii.wms.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactsService {

    private final ContactsRepository contactsRepository;

    public Contacts getById(Long id) {
        return contactsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The Contacts not fount by id: " + id)
        );
    }

    public Contacts update(Long contactsId, ContactsUpdateRequest request) {
        Contacts contacts = getById(contactsId);

        contacts.setPhone(request.getPhone());
        contacts.setFirstName(request.getFirstName());
        contacts.setLastName(request.getLastName());
        contacts.setBio(request.getBio());
        contacts.setSocialNetworks(request.getSocialNetworks());

        return contactsRepository.save(contacts);
    }
}
