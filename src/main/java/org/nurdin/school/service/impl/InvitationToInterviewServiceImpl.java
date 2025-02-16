package org.nurdin.school.service.impl;

import org.nurdin.school.entity.InvitationToInterviewForWorkEntity;
import org.nurdin.school.repository.InvitationToInterviewRepository;
import org.nurdin.school.service.InvitationToInterviewService;
import org.springframework.stereotype.Service;

@Service
public class InvitationToInterviewServiceImpl implements InvitationToInterviewService {
    InvitationToInterviewRepository invitationToInterviewRepository;

    public InvitationToInterviewServiceImpl(InvitationToInterviewRepository invitationToInterviewRepository) {
        this.invitationToInterviewRepository = invitationToInterviewRepository;

    }
    @Override
    public InvitationToInterviewForWorkEntity save(InvitationToInterviewForWorkEntity invitationToInterviewForWorkEntity) {
        return invitationToInterviewRepository.save(invitationToInterviewForWorkEntity);
    }
}
