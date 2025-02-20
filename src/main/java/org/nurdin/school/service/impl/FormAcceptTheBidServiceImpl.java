package org.nurdin.school.service.impl;

import org.nurdin.school.entity.FormAcceptTheBidForStudyEntity;
import org.nurdin.school.repository.FormAcceptTheBidRepository;
import org.nurdin.school.service.FormAcceptTheBidService;
import org.springframework.stereotype.Service;

@Service
public class FormAcceptTheBidServiceImpl implements FormAcceptTheBidService {
    private final FormAcceptTheBidRepository formAcceptTheBidRepository;

    public FormAcceptTheBidServiceImpl(FormAcceptTheBidRepository formAcceptTheBidRepository) {
        this.formAcceptTheBidRepository = formAcceptTheBidRepository;
    }

    @Override
    public FormAcceptTheBidForStudyEntity saveFormAcceptTheBid(FormAcceptTheBidForStudyEntity formAcceptTheBid) {
        return formAcceptTheBidRepository.save(formAcceptTheBid);
    }
}
