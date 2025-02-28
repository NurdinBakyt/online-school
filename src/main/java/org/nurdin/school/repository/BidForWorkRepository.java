package org.nurdin.school.repository;

import org.jetbrains.annotations.NotNull;
import org.nurdin.school.entity.BidForStudyEntity;
import org.nurdin.school.entity.BidForWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BidForWorkRepository extends JpaRepository <BidForWorkEntity , Long> {

    Optional<BidForWorkEntity> findById(Long id);
    BidForWorkEntity findByEmail(String email);
}
