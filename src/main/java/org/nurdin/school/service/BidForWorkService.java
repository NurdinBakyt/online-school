package org.nurdin.school.service;

import org.nurdin.school.entity.BidForWorkEntity;

import java.util.List;
import java.util.Optional;

public interface BidForWorkService {
    Optional<BidForWorkEntity> findById(Long id);
    void saveBidForWork(BidForWorkEntity bidForWorkEntity);
    void deleteBidForWork();
    void updateBidForWork(BidForWorkEntity bidForWorkEntity);
    List<BidForWorkEntity> getAllBidForWork();
    BidForWorkEntity getBidForWorkByUserEmail(String userEmail);




}
