package org.nurdin.school.repository;

import org.nurdin.school.entity.BidForWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BidForWorkRepository extends JpaRepository <BidForWorkEntity , Long> {

    Optional<BidForWorkEntity> findById(Long id);
    BidForWorkEntity findByEmail(String email);
}
