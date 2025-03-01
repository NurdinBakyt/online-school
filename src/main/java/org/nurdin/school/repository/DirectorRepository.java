package org.nurdin.school.repository;

import org.nurdin.school.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<DirectorEntity,Long> {
}
