package com.dmtryii.wms.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ContactsDTO {
    @Email
    @NotEmpty(message = "username should not be empty")
    private String email;
    private String phone;
    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String firstName;
    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String lastName;
    private String bio;
    private List<String> socialNetworks;
}
