package org.nurdin.school.service;

import org.nurdin.school.entity.BidForWorkEntity;

import java.util.List;

public interface BidForWorkService {
    void saveBidForWork(BidForWorkEntity bidForWorkEntity);
    void deleteBidForWork();
    void updateBidForWork(BidForWorkEntity bidForWorkEntity);
    List<BidForWorkEntity> getAllBidForWork();
    BidForWorkEntity getBidForWorkByUserEmail(String userEmail);




}
