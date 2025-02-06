package org.nurdin.school.controller;

import org.nurdin.school.dto.InvitationToInterviewDTO;
import org.nurdin.school.entity.InvitationToInterviewEntity;
import org.nurdin.school.service.InvitationToInterviewService;
import org.nurdin.school.util.InvitationToInterviewMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

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
    public ResponseEntity<String> createInvitation (InvitationToInterviewDTO invitationToInterviewDTO) {
        InvitationToInterviewEntity invitationToInterviewEntity = invitationToInterviewMapper.invitationToInterviewDTOtoEntity(invitationToInterviewDTO);
        invitationToInterviewService.save(invitationToInterviewEntity);

        return ResponseEntity.ok("Приглашение создано");

    }

}
