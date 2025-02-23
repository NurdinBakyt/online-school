package org.nurdin.school.util;


import org.nurdin.school.dto.InvitationToInterviewForWorkDTO;
import org.nurdin.school.entity.InvitationToInterviewForWorkEntity;
import org.springframework.stereotype.Component;

@Component
public class InvitationToInterviewMapper {
    //InvitationToInterviewForWorkDTO ==> InvitationToInterviewForWorkEntity
    public InvitationToInterviewForWorkEntity invitationToInterviewDTOtoEntity(InvitationToInterviewForWorkDTO invitationToInterviewForWorkDTO) {
        InvitationToInterviewForWorkEntity invitationToInterviewForWorkEntity = new InvitationToInterviewForWorkEntity();
        invitationToInterviewForWorkEntity.setInterviewDate(invitationToInterviewForWorkDTO.getInterviewDate());
        invitationToInterviewForWorkEntity.setInvitation(invitationToInterviewForWorkDTO.getInvitation());


        return invitationToInterviewForWorkEntity;
    }
}
