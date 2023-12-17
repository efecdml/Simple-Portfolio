package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WorksDetailedCardRepository extends JpaRepository<WorksDetailedCard, Integer> {
    Set<WorksDetailedCard> findAllByLocale_Name(String localeName);

    @Query("select wdc.imageName from WorksDetailedCard wdc where wdc.id = ?1")
    String findImageNameById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard(wdc.id, wdc.imageName, wdc.works.id, wdc.locale.id) " +
            "from WorksDetailedCard wdc where wdc.id = ?1")
    Optional<WorksDetailedCard> findIdAndImageNameAndWorksAndLocaleById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard(wdc.id, wdc.imageName) " +
            "from WorksDetailedCard wdc where wdc.id = ?1")
    Optional<WorksDetailedCard> findIdAndImageNameById(int id);
}
