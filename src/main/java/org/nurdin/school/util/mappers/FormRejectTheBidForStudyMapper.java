package org.nurdin.school.util.mappers;

import org.nurdin.school.dto.FormRejectTheBidForStudyDTO;
import org.nurdin.school.entity.FormRejectTheBidForStudyEntity;
import org.springframework.stereotype.Component;

@Component
public class FormRejectTheBidForStudyMapper {

    //FormRejectTheBidForStudyDTO ==> FormRejectTheBidForStudyEntity
    public static FormRejectTheBidForStudyEntity formRejectionForTheBidDTOtoEntity(FormRejectTheBidForStudyDTO formRejectTheBidForStudyDTO) {
        FormRejectTheBidForStudyEntity formRejection = new FormRejectTheBidForStudyEntity();
        formRejection.setMailForRejectTheBid(formRejectTheBidForStudyDTO.getMailForRejectTheBid());
        return formRejection;
    }

}
