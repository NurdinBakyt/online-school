package org.nurdin.school.util;

import org.nurdin.school.dto.InvitationToInterviewDTO;
import org.nurdin.school.dto.RejectionOfTheBidDTO;
import org.nurdin.school.entity.InvitationToInterviewEntity;
import org.nurdin.school.entity.RejectionOfTheBidEntity;
import org.springframework.stereotype.Component;

@Component
public class RejectionOfTheBidMapper {
    //RejectionOfTheBidDTO ==> RejectionOfTheBidEntity
    public RejectionOfTheBidEntity rejectionOfTheBidDTOtoEntity (RejectionOfTheBidDTO rejectionOfTheBidDTO) {
        RejectionOfTheBidEntity rejectionOfTheBidEntity = new RejectionOfTheBidEntity();
        rejectionOfTheBidEntity.setMail(rejectionOfTheBidDTO.getMail());
        return rejectionOfTheBidEntity;
    }
}
