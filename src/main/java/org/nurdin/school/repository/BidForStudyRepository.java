package org.nurdin.school.repository;

import org.nurdin.school.entity.BidForStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidForStudyRepository extends JpaRepository<BidForStudyEntity , Long> {
    BidForStudyEntity findBidForStudyEntityByEmailParent(String email);
}
