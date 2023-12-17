package com.gungorefe.simpleportfolio.repository.page;

import com.gungorefe.simpleportfolio.entity.page.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {
    Boolean existsByLocale_Name(String localeName);

    @Query("select h from Home h left join fetch h.homeCarouselSections where h.locale.name = ?1")
    Optional<Home> findWithCarouselSectionsByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.Home(h.id, h.locale.id) from Home h " +
            "where h.locale.name = ?1")
    Optional<Home> findIdAndLocaleIdByLocale_Name(String localeName);
}
