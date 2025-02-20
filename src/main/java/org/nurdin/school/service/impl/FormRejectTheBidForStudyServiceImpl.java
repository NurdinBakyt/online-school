package org.nurdin.school.service.impl;

import org.nurdin.school.entity.FormRejectTheBidForStudyEntity;
import org.nurdin.school.repository.FormAcceptTheBidRepository;
import org.nurdin.school.repository.FormRejectTheBidForStudyRepository;
import org.nurdin.school.service.FormRejectTheBidForStudyService;
import org.springframework.stereotype.Service;

@Service
public class FormRejectTheBidForStudyServiceImpl implements FormRejectTheBidForStudyService {

    private final FormRejectTheBidForStudyRepository formRejectTheBidForStudyRepository;

    public FormRejectTheBidForStudyServiceImpl(FormAcceptTheBidRepository formAcceptTheBidRepository, FormRejectTheBidForStudyRepository formRejectTheBidForStudyRepository) {
        this.formRejectTheBidForStudyRepository = formRejectTheBidForStudyRepository;

    }

    @Override
    public FormRejectTheBidForStudyEntity save(FormRejectTheBidForStudyEntity formRejectTheBidForStudy) {
        return formRejectTheBidForStudyRepository.save(formRejectTheBidForStudy);
    }
}
