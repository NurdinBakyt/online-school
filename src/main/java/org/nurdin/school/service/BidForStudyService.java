package org.nurdin.school.service;

import org.nurdin.school.entity.BidForStudyEntity;

import java.util.List;

public interface BidForStudyService {
    BidForStudyEntity saveBidForStudy(BidForStudyEntity bidForStudy);
    BidForStudyEntity findByEmailParent(String email);
    List<BidForStudyEntity> getAllBidForStudy();
}
