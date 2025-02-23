package org.nurdin.school.util;

import org.nurdin.school.dto.DirectorDTO;
import org.nurdin.school.dto.EmployeeDTO;
import org.nurdin.school.entity.DirectorEntity;
import org.nurdin.school.entity.EmployeeEntity;

public class EmployeeMapper {
//    EmployeeDTO ==> EmployeeEntity
    public static EmployeeEntity employeeDTOtoEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDTO.getId());
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setAge(employeeDTO.getAge());
        employeeEntity.setPhone(employeeDTO.getPhone());
        employeeEntity.setResume(employeeDTO.getResume());
        employeeEntity.setSurname(employeeDTO.getSurname());
//      DirectorEntity ==> DirectorDTO
        employeeEntity.setDirectorEntity(DirectorMapper.directorEntityToDTO(employeeDTO.getDirectorDTO()));
        return employeeEntity;
    }
//    EmployeeEntity ==> EmployeeDTO
    public static EmployeeDTO employeeEntityToEmployeeDTO(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setSurname(employeeEntity.getSurname());
        employeeDTO.setAge(employeeEntity.getAge());
        employeeDTO.setPhone(employeeEntity.getPhone());
        employeeDTO.setResume(employeeEntity.getResume());
        return employeeDTO;
    }
}
