package org.nurdin.school.util;

import org.nurdin.school.dto.BidForStudyDTO;
import org.nurdin.school.dto.response.BidForStudyResponse;
import org.nurdin.school.dto.response.BidForWorkResponse;
import org.nurdin.school.entity.BidForStudyEntity;
import org.springframework.stereotype.Component;

@Component
public class BidForStudyMapper {

    public static BidForStudyEntity bidForStudyDTOToEntity(BidForStudyDTO bidForStudyDTO) {
        BidForStudyEntity bidForStudyEntity = new BidForStudyEntity();
        bidForStudyEntity.setBidParent(bidForStudyDTO.getBidParent());
        bidForStudyEntity.setChildBirthCertificate(bidForStudyDTO.getChildBirthCertificate());
        bidForStudyEntity.setPlaceOfResidence(bidForStudyDTO.getPlaceOfResidence());
        bidForStudyEntity.setPassportParent(bidForStudyDTO.getPassportParent());
        bidForStudyEntity.setNameParent(bidForStudyDTO.getNameParent());
        bidForStudyEntity.setSurnameParent(bidForStudyDTO.getSurnameParent());
        bidForStudyEntity.setPatronymicParent(bidForStudyDTO.getPatronymicParent());
        bidForStudyEntity.setEmailParent(bidForStudyDTO.getEmailParent());
        bidForStudyEntity.setPhoneNumberParent(bidForStudyDTO.getPhoneNumberParent());

        bidForStudyEntity.setChildMedicalCertificateCopy(bidForStudyDTO.getChildMedicalCertificateCopy());
        bidForStudyEntity.setChildMedicalRecord(bidForStudyDTO.getChildMedicalRecord());
        bidForStudyEntity.setAgeChild(bidForStudyDTO.getAgeChild());
        bidForStudyEntity.setSurnameChild(bidForStudyDTO.getSurnameChild());
        bidForStudyEntity.setPatronymicChild(bidForStudyDTO.getPatronymicChild());
        bidForStudyEntity.setChildClass(bidForStudyDTO.getChildClass());

        return bidForStudyEntity;
    }

    public static BidForStudyResponse createBidForStudyResponse(BidForStudyEntity bidForStudyEntity , String userName) {
        BidForStudyResponse bidForStudyResponse = new BidForStudyResponse();
        bidForStudyResponse.setBidParent(bidForStudyEntity.getBidParent());
        bidForStudyResponse.setBidStatus(bidForStudyEntity.getBidStatus());
        bidForStudyResponse.setChildBirthCertificate(bidForStudyEntity.getChildBirthCertificate());
        bidForStudyResponse.setPlaceOfResidence(bidForStudyEntity.getPlaceOfResidence());
        bidForStudyResponse.setPassportParent(bidForStudyEntity.getPassportParent());
        bidForStudyResponse.setNameParent(bidForStudyEntity.getNameParent());
        bidForStudyResponse.setSurnameParent(bidForStudyEntity.getSurnameParent());
        bidForStudyResponse.setPatronymicParent(bidForStudyEntity.getPatronymicParent());
        bidForStudyResponse.setEmailParent(bidForStudyEntity.getEmailParent());
        bidForStudyResponse.setPhoneNumberParent(bidForStudyEntity.getPhoneNumberParent());

        bidForStudyResponse.setChildMedicalCertificateCopy(bidForStudyEntity.getChildMedicalCertificateCopy());
        bidForStudyResponse.setChildMedicalRecord(bidForStudyEntity.getChildMedicalRecord());
        bidForStudyResponse.setAgeChild(bidForStudyEntity.getAgeChild());
        bidForStudyResponse.setSurnameChild(bidForStudyEntity.getSurnameChild());
        bidForStudyResponse.setPatronymicChild(bidForStudyEntity.getPatronymicChild());
        bidForStudyResponse.setChildClass(bidForStudyEntity.getChildClass());

        bidForStudyResponse.setUserName(userName);
        return bidForStudyResponse;
    }

}
