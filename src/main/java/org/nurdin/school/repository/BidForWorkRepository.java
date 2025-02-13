package org.nurdin.school.repository;

import org.nurdin.school.entity.BidForStudyEntity;
import org.nurdin.school.entity.BidForWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidForWorkRepository extends JpaRepository <BidForWorkEntity , Long> {

    BidForWorkEntity findByEmail(String email);
}
