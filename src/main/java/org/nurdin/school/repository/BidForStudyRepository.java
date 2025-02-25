package org.nurdin.school.repository;

import org.nurdin.school.entity.BidForStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BidForStudyRepository extends JpaRepository<BidForStudyEntity , Long> {
    Optional<BidForStudyEntity> findBidForStudyEntityById(Long id);
    BidForStudyEntity findBidForStudyEntityByEmailParent(String email);
}
