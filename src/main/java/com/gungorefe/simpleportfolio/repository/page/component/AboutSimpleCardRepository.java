package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AboutSimpleCardRepository extends JpaRepository<AboutSimpleCard, Integer> {
    Set<AboutSimpleCard> findAllByLocale_Name(String localeName);

    @Query("select asc.imageName from AboutSimpleCard asc where asc.id = ?1")
    String findImageNameById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard(asc.id, asc.imageName, asc.about.id, asc.locale.id) " +
            "from AboutSimpleCard asc where asc.id = ?1")
    Optional<AboutSimpleCard> findIdAndImageNameAndAboutAndLocaleById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard(asc.id, asc.imageName) " +
            "from AboutSimpleCard asc where asc.id = ?1")
    Optional<AboutSimpleCard> findIdAndImageNameById(int id);
}
