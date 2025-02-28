package org.nurdin.school.service;

import org.nurdin.school.entity.EmployeeEntity;

public interface EmployeeService {
    EmployeeEntity bidToEmployee(EmployeeEntity employee,Long directorId);
    EmployeeEntity updateEmployee(EmployeeEntity employee);
    EmployeeEntity deleteEmployee(EmployeeEntity employee);
    EmployeeEntity getEmployeeById(Long id);
    EmployeeEntity getEmployeeByUsername(String username);
    EmployeeEntity getEmployeeByEmail(String email);
    void rejectEmployee(Long id);
    void acceptEmployee(Long id);
}
