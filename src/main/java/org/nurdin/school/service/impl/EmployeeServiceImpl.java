package org.nurdin.school.service.impl;

import org.nurdin.school.entity.DirectorEntity;
import org.nurdin.school.entity.EmployeeEntity;
import org.nurdin.school.repository.DirectorRepository;
import org.nurdin.school.repository.EmployeeRepository;
import org.nurdin.school.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DirectorRepository directorRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DirectorRepository directorRepository) {
        this.employeeRepository = employeeRepository;
        this.directorRepository = directorRepository;
    }


    @Override
    public EmployeeEntity bidToEmployee(EmployeeEntity employee,Long directorId) {
        DirectorEntity director = directorRepository.findById(directorId).
                orElseThrow(() -> new RuntimeException("директор не найден"));

        employee.setDirectorEntity(director);
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public EmployeeEntity deleteEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        return null;
    }

    @Override
    public EmployeeEntity getEmployeeByUsername(String username) {
        return null;
    }

    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public void rejectEmployee(Long id) {

    }

    @Override
    public void acceptEmployee(Long id) {

    }
}
