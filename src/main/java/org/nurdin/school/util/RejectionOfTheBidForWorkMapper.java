package org.nurdin.school.util;

import org.nurdin.school.dto.RejectionOfTheBidForWorkDTO;
import org.nurdin.school.entity.RejectionOfTheBidForWorkEntity;
import org.springframework.stereotype.Component;

@Component
public class RejectionOfTheBidForWorkMapper {
    //RejectionOfTheBidForWorkDTO ==> RejectionOfTheBidForWorkEntity
    public RejectionOfTheBidForWorkEntity rejectionOfTheBidDTOtoEntity (RejectionOfTheBidForWorkDTO rejectionOfTheBidForWorkDTO) {
        RejectionOfTheBidForWorkEntity rejectionOfTheBidForWorkEntity = new RejectionOfTheBidForWorkEntity();
        rejectionOfTheBidForWorkEntity.setMail(rejectionOfTheBidForWorkDTO.getMail());
        return rejectionOfTheBidForWorkEntity;
    }
}
