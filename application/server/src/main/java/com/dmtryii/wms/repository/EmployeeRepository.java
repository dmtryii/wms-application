package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByUserUsername(String username);
}
