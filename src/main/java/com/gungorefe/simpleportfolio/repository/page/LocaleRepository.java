package com.gungorefe.simpleportfolio.repository.page;

import com.gungorefe.simpleportfolio.entity.page.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, Integer> {
    Boolean existsByName(String name);
}
