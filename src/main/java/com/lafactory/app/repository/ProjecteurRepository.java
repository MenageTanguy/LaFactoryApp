package com.lafactory.app.repository;

import com.lafactory.app.domain.Projecteur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Projecteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjecteurRepository extends JpaRepository<Projecteur, Long> {
}
