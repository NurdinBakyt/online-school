package org.nurdin.school.util.mappers;

import org.nurdin.school.dto.BidForWorkDTO;
import org.nurdin.school.dto.response.BidForWorkResponse;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BidForWorkMapper {


    //метод который BidForWorkDTO преобразует в BidForWorkEntity
    public static BidForWorkEntity bidForWorkDtoToEntity (BidForWorkDTO bidForWorkDTO) {
        BidForWorkEntity bidForWorkEntity = new BidForWorkEntity();
        bidForWorkEntity.setResume(bidForWorkDTO.getResume());
        bidForWorkEntity.setPassport(bidForWorkDTO.getPassport());
        bidForWorkEntity.setName(bidForWorkDTO.getName());
        bidForWorkEntity.setSurname(bidForWorkDTO.getSurname());
        bidForWorkEntity.setPatronymic(bidForWorkDTO.getPatronymic());
        bidForWorkEntity.setAge(bidForWorkDTO.getAge());
        bidForWorkEntity.setPhoneNumber(bidForWorkDTO.getPhoneNumber());
        bidForWorkEntity.setEmail(bidForWorkDTO.getEmail());
        return bidForWorkEntity;
    }


    //метод, который берет UserEntity и BidForWork по имейлу определенного юзера и собирает в Response
    public static BidForWorkResponse bidForWorkEntityAndUserEntityToBidForWorkAcceptResponse(UserEntity userEntity , BidForWorkEntity bidForWorkEntity) {
        BidForWorkResponse bidForWorkResponse = new BidForWorkResponse();
        bidForWorkResponse.setId(bidForWorkEntity.getId());
        bidForWorkResponse.setUserName(userEntity.getUsername());
        bidForWorkResponse.setResume(bidForWorkEntity.getResume());
        bidForWorkResponse.setPassport(bidForWorkEntity.getPassport());
        bidForWorkResponse.setBidStatus(bidForWorkEntity.getBidStatus());
        bidForWorkResponse.setName(bidForWorkEntity.getName());
        bidForWorkResponse.setSurname(bidForWorkEntity.getSurname());
        bidForWorkResponse.setPatronymic(bidForWorkEntity.getPatronymic());
        bidForWorkResponse.setAge(bidForWorkEntity.getAge());
        bidForWorkResponse.setPhoneNumber(bidForWorkEntity.getPhoneNumber());
        bidForWorkResponse.setEmail(bidForWorkEntity.getEmail());

        return bidForWorkResponse;

    }
}
