package com.gungorefe.simpleportfolio.setup;

import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.entity.page.Locale;
import com.gungorefe.simpleportfolio.repository.page.HomeRepository;
import com.gungorefe.simpleportfolio.repository.page.LocaleRepository;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Profile("!test")
@Transactional
@Component
public class PageSetup implements ApplicationListener<ContextRefreshedEvent> {
    private final LocaleRepository localeRepository;
    private final HomeRepository homeRepository;
    private Locale localeEnglish;
    private Locale localeTurkish;

    private void insertLocalesIfNotExist() {
        if (!localeRepository.existsByName(LocaleName.ENGLISH.value)) {
            log.warn("English locale not found, inserting..");
            localeEnglish = localeRepository.save(new Locale(LocaleName.ENGLISH.value));
        }
        if (!localeRepository.existsByName(LocaleName.TURKISH.value)) {
            log.warn("Turkish locale not found, inserting..");
            localeTurkish = localeRepository.save(new Locale(LocaleName.TURKISH.value));
        }
    }

    private void insertPagesIfNotExist() {
        if (!homeRepository.existsByLocale_Name(LocaleName.ENGLISH.value)) {
            log.warn("English Home page not found, inserting..");
            homeRepository.save(new Home("", "", "", "", localeEnglish));
        }
        if (!homeRepository.existsByLocale_Name(LocaleName.TURKISH.value)) {
            log.warn("Turkish Home page not found, inserting..");
            homeRepository.save(new Home("", "", "", "", localeTurkish));
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        insertLocalesIfNotExist();
        insertPagesIfNotExist();
    }
}
