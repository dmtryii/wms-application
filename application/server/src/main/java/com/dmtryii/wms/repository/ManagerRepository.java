package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Manager;
import com.dmtryii.wms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findManagerByUser(User user);
    Manager findManagerByUserUsername(String username);
}
