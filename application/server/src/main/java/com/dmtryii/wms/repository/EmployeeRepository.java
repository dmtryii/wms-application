package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Employee;
import com.dmtryii.wms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByUserUsername(String username);
    Employee findEmployeeByUser(User user);
}
