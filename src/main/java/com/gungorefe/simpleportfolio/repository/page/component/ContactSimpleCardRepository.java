package com.gungorefe.simpleportfolio.repository.page.component;

import com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ContactSimpleCardRepository extends JpaRepository<ContactSimpleCard, Integer> {
    Set<ContactSimpleCard> findAllByLocale_Name(String localeName);

    @Query("select csc.imageName from ContactSimpleCard csc where csc.id = ?1")
    String findImageNameById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard(csc.id, csc.imageName, csc.contact.id, csc.locale.id) " +
            "from ContactSimpleCard csc where csc.id = ?1")
    Optional<ContactSimpleCard> findIdAndImageNameAndContactAndLocaleById(int id);

    @Query("select new com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard(csc.id, csc.imageName) " +
            "from ContactSimpleCard csc where csc.id = ?1")
    Optional<ContactSimpleCard> findIdAndImageNameById(int id);
}
