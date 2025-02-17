package org.nurdin.school.util;

import org.nurdin.school.dto.DirectorDTO;
import org.nurdin.school.entity.DirectorEntity;

public class DirectorMapper {
//    DirectorDTO ==> DirectorEntity
    public static DirectorDTO directorDTOtoEntity(DirectorEntity directorEntity) {
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setId(directorEntity.getId());
        directorDTO.setName(directorEntity.getName());
        directorDTO.setEmail(directorEntity.getEmail());
        directorDTO.setSurname(directorEntity.getSurname());
        return directorDTO;
    }
//    DirectorEntity ==> DirectorDTO
    public static DirectorEntity directorEntityToDTO(DirectorDTO directorDTO) {
        DirectorEntity directorEntity = new DirectorEntity();
        directorEntity.setId(directorDTO.getId());
        directorEntity.setName(directorDTO.getName());
        directorEntity.setEmail(directorDTO.getEmail());
        directorEntity.setSurname(directorDTO.getSurname());
        return directorEntity;
    }
}
