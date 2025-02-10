package org.nurdin.school.service.impl;

import org.nurdin.school.entity.InvitationToInterviewEntity;
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
    public InvitationToInterviewEntity save(InvitationToInterviewEntity invitationToInterviewEntity) {
        return invitationToInterviewRepository.save(invitationToInterviewEntity);
    }
}
