package com.gungorefe.simpleportfolio.repository.page;

import com.gungorefe.simpleportfolio.entity.page.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutRepository extends JpaRepository<About, Integer> {
    Optional<About> findByLocale_Name(String localeName);

    @Query("select a.imageName from About a where a.locale.name = ?1")
    String findImageNameByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.About(a.id, a.imageName, a.locale.id) from About a " +
            "where a.locale.name = ?1")
    Optional<About> findIdAndImageNameAndLocaleIdByLocale_Name(String localeName);

    Boolean existsByLocale_Name(String localeName);
}
