package org.nurdin.school.service;

import org.nurdin.school.entity.EmployeeEntity;

import java.util.List;

public interface DirectorService {
    List<EmployeeEntity> getEmployeesBid(Long directorId);
}
