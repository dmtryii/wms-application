package com.dmtryii.wms.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ContactsUpdateRequest {
    private Long id;
    private String phone;
    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String firstName;
    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String lastName;
    @Column(columnDefinition = "text")
    private String bio;
    private List<String> socialNetworks;
}
