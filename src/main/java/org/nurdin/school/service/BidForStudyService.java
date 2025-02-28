package org.nurdin.school.service;

import org.nurdin.school.entity.BidForStudyEntity;

import java.util.List;
import java.util.Optional;

public interface BidForStudyService {

    Optional<BidForStudyEntity> findById(Long id);
    BidForStudyEntity saveBidForStudy(BidForStudyEntity bidForStudy);
    BidForStudyEntity findByEmailParent(String email);
    List<BidForStudyEntity> getAllBidForStudy();
}
