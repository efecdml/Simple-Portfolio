package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HomeCarouselSectionRepository extends JpaRepository<HomeCarouselSection, Integer> {
    Set<HomeCarouselSection> findAllByLocale_Name(String localeName);

    @Query("select hcs.imageName from HomeCarouselSection hcs where hcs.id = ?1")
    String findImageNameById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection(hcs.id, hcs.imageName, hcs.home.id, hcs.locale.id) " +
            "from HomeCarouselSection hcs where hcs.id = ?1 and hcs.locale.name = ?2")
    Optional<HomeCarouselSection> findIdAndImageNameAndHomeAndLocaleByIdAndLocale_Name(int id, String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection(hcs.id, hcs.imageName) " +
            "from HomeCarouselSection hcs where hcs.id = ?1 and hcs.locale.name = ?2")
    Optional<HomeCarouselSection> findIdAndImageNameByIdAndLocale_Name(int id, String localeName);
}
