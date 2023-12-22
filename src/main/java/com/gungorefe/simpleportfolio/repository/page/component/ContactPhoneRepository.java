package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.ContactPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhone, Integer> {
    Set<ContactPhone> findAllByLocale_Name(String localeName);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.ContactPhone(cp.id, cp.contact.id, cp.locale.id) " +
            "from ContactPhone cp where cp.id = ?1 and cp.locale.name = ?2")
    Optional<ContactPhone> findIdAndContactAndLocaleByIdAndLocale_Name(int id, String localeName);

    Boolean existsByIdAndLocale_Name(int id, String localeName);
}
