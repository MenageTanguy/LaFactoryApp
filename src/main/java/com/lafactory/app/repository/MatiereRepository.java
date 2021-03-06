package com.lafactory.app.repository;

import com.lafactory.app.domain.Matiere;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Matiere entity.
 */
@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    @Query(value = "select distinct matiere from Matiere matiere left join fetch matiere.formateurs",
        countQuery = "select count(distinct matiere) from Matiere matiere")
    Page<Matiere> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct matiere from Matiere matiere left join fetch matiere.formateurs")
    List<Matiere> findAllWithEagerRelationships();

    @Query("select matiere from Matiere matiere left join fetch matiere.formateurs where matiere.id =:id")
    Optional<Matiere> findOneWithEagerRelationships(@Param("id") Long id);
}
