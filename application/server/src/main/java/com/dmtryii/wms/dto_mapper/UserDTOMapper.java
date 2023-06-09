package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.UserDTO;
import com.dmtryii.wms.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getContacts(),
                user.getAddress(),
                user.getCreateData(),
                user.getRoles()
        );
    }
}
