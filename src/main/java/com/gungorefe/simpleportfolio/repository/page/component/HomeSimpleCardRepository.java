package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HomeSimpleCardRepository extends JpaRepository<HomeSimpleCard, Integer> {
    Set<HomeSimpleCard> findAllByLocale_Name(String localeName);

    @Query("select hsc.imageName from HomeSimpleCard hsc where hsc.id = ?1")
    String findImageNameById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard(hsc.id, hsc.imageName, hsc.home.id, hsc.locale.id) " +
            "from HomeSimpleCard hsc where hsc.id = ?1")
    Optional<HomeSimpleCard> findIdAndImageNameAndHomeAndLocaleById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard(hsc.id, hsc.imageName) " +
            "from HomeSimpleCard hsc where hsc.id = ?1")
    Optional<HomeSimpleCard> findIdAndImageNameById(int id);
}
