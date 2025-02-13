package org.nurdin.school.util;


import org.nurdin.school.dto.InvitationToInterviewDTO;
import org.nurdin.school.entity.InvitationToInterviewEntity;
import org.springframework.stereotype.Component;

@Component
public class InvitationToInterviewMapper {
    //InvitationToInterviewDTO ==> InvitationToInterviewEntity
    public InvitationToInterviewEntity invitationToInterviewDTOtoEntity(InvitationToInterviewDTO invitationToInterviewDTO) {
        InvitationToInterviewEntity invitationToInterviewEntity = new InvitationToInterviewEntity();
        invitationToInterviewEntity.setInterviewDate(invitationToInterviewDTO.getInterviewDate());
        invitationToInterviewEntity.setInvitation(invitationToInterviewDTO.getInvitation());


        return invitationToInterviewEntity;
    }



}
