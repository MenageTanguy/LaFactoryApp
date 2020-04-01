package com.lafactory.app.repository;

import com.lafactory.app.domain.Formateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Formateur entity.
 */
@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    @Query(value = "select distinct formateur from Formateur formateur left join fetch formateur.matieresDebutants left join fetch formateur.matieresIntermedaires left join fetch formateur.matieresAvances left join fetch formateur.matieresConfirmes",
        countQuery = "select count(distinct formateur) from Formateur formateur")
    Page<Formateur> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct formateur from Formateur formateur left join fetch formateur.matieresDebutants left join fetch formateur.matieresIntermedaires left join fetch formateur.matieresAvances left join fetch formateur.matieresConfirmes")
    List<Formateur> findAllWithEagerRelationships();

    @Query("select formateur from Formateur formateur left join fetch formateur.matieresDebutants left join fetch formateur.matieresIntermedaires left join fetch formateur.matieresAvances left join fetch formateur.matieresConfirmes where formateur.id =:id")
    Optional<Formateur> findOneWithEagerRelationships(@Param("id") Long id);
}
