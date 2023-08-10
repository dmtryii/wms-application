package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.ManagerRequest;
import com.dmtryii.wms.exception.RoleException;
import com.dmtryii.wms.exception.UserNotFoundException;
import com.dmtryii.wms.model.Manager;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserService userService;

    public Manager createManager(ManagerRequest request, Principal principal) {

        String username = request.getUsername();
        Manager _manager = managerRepository.findManagerByUserUsername(username);

        if(_manager != null) {
            throw new RoleException("The user already has this role");
        }

        User user = userService.getUserByUsername(username);
        User whoCreated = userService.getUserByPrincipal(principal);
        user.getRoles().add(ERole.MANAGER);

        Manager manager = Manager.builder()
                .user(user)
                .whoCreated(whoCreated)
                .createData(LocalDateTime.now())
                .build();
        return managerRepository.save(manager);
    }

    public Manager getManagerById(Long managerId) {
        return managerRepository.findById(managerId).orElseThrow(
                () -> new UserNotFoundException("Manager not fount by id: " + managerId)
        );
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerByPrincipal(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return managerRepository.findManagerByUser(user);
    }
}
