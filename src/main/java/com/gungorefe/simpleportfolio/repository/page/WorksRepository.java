package com.gungorefe.simpleportfolio.repository.page;

import com.gungorefe.simpleportfolio.entity.page.Works;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorksRepository extends JpaRepository<Works, Integer> {
    @Query("select w from Works w left join fetch w.worksDetailedCards where w.locale.name = ?1")
    Optional<Works> findWithWorksDetailedCardByLocale_Name(String localeName);

    @Query("select w.imageName from Works w where w.locale.name = ?1")
    String findImageNameByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.Works(w.id, w.imageName, w.locale.id) from Works w " +
            "where w.locale.name = ?1")
    Optional<Works> findIdAndImageNameAndLocaleIdByLocale_Name(String localeName);

    Boolean existsByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.Works(w.id, w.locale.id) from Works w " +
            "where w.locale.name = ?1")
    Optional<Works> findIdAndLocaleIdByLocale_Name(String localeName);
}
