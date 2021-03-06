package com.lafactory.app.repository;

import com.lafactory.app.domain.Salle;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Salle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
}
