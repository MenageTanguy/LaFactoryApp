package com.lafactory.app.repository;

import com.lafactory.app.domain.Cursus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Cursus entity.
 */
@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {

    @Query(value = "select distinct cursus from Cursus cursus left join fetch cursus.stagiaires left join fetch cursus.modules",
        countQuery = "select count(distinct cursus) from Cursus cursus")
    Page<Cursus> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct cursus from Cursus cursus left join fetch cursus.stagiaires left join fetch cursus.modules")
    List<Cursus> findAllWithEagerRelationships();

    @Query("select cursus from Cursus cursus left join fetch cursus.stagiaires left join fetch cursus.modules where cursus.id =:id")
    Optional<Cursus> findOneWithEagerRelationships(@Param("id") Long id);
}
