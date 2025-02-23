package org.nurdin.school.controller;

import org.nurdin.school.dto.InvitationToInterviewForWorkDTO;
import org.nurdin.school.entity.InvitationToInterviewForWorkEntity;
import org.nurdin.school.service.InvitationToInterviewService;
import org.nurdin.school.util.mappers.InvitationToInterviewMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api/v1/invitation-to-interview")
public class InvitationToInterviewController {

    private final InvitationToInterviewMapper invitationToInterviewMapper;
    private final InvitationToInterviewService invitationToInterviewService;


    public InvitationToInterviewController(InvitationToInterviewMapper invitationToInterviewMapper, InvitationToInterviewService invitationToInterviewService) {
        this.invitationToInterviewMapper = invitationToInterviewMapper;
        this.invitationToInterviewService = invitationToInterviewService;
    }


    @PostMapping(name = "/create-invitation")
    public ResponseEntity<String> createInvitation (InvitationToInterviewForWorkDTO invitationToInterviewForWorkDTO) {
        InvitationToInterviewForWorkEntity invitationToInterviewForWorkEntity = invitationToInterviewMapper.invitationToInterviewDTOtoEntity(invitationToInterviewForWorkDTO);
        invitationToInterviewService.save(invitationToInterviewForWorkEntity);

        return ResponseEntity.ok("Приглашение создано");

    }

}
