package com.gungorefe.simpleportfolio.repository.page;

import com.gungorefe.simpleportfolio.entity.page.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.Contact(c.id, c.locale.id) from Contact c " +
            "where c.locale.name = ?1")
    Optional<Contact> findIdAndLocaleIdByLocale_Name(String localeName);

    Boolean existsByLocale_Name(String localeName);
}
