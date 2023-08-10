package com.dmtryii.wms.dto.response;

import com.dmtryii.wms.model.enums.ERole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 2, max = 30, message = "username should be between 2 and 30 characters")
    private String username;
    private ContactsDTO contacts;
    private AddressDTO address;
    Set<ERole> roles;
}
