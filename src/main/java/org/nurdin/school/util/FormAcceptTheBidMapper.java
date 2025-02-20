package org.nurdin.school.util;

import org.nurdin.school.dto.FormAcceptTheBidForStudyDTO;
import org.nurdin.school.entity.FormAcceptTheBidForStudyEntity;
import org.springframework.stereotype.Component;

@Component
public class FormAcceptTheBidMapper {

    //FormAcceptTheBidForStudyDTO ==> FormAcceptTheBidForStudyEntity
    public static FormAcceptTheBidForStudyEntity formAcceptTheBidDTOtoEntity(FormAcceptTheBidForStudyDTO formAcceptTheBidForStudyDTO){
        FormAcceptTheBidForStudyEntity formAcceptTheBidForStudyEntity = new FormAcceptTheBidForStudyEntity();
        formAcceptTheBidForStudyEntity.setMailForAcceptBid(formAcceptTheBidForStudyDTO.getMailForAcceptBid());
        formAcceptTheBidForStudyEntity.setMeetingDate(formAcceptTheBidForStudyDTO.getMeetingDate());
        return formAcceptTheBidForStudyEntity;
    };



}
