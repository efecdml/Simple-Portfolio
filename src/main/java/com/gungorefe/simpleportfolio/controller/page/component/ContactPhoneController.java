package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.ContactPhoneDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactPhoneRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactPhoneRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/contact/component/phone")
@RestController
public class ContactPhoneController {
    private final ComponentService service;

    @PostMapping("/competent/locale/{localeName}")
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestBody CreateContactPhoneRequest request
    ) {
        service.create(
                ComponentName.CONTACT_PHONE,
                null,
                request,
                localeName,
                PageName.CONTACT
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ContactPhoneDto> getDto(@PathVariable int id) {
        ContactPhoneDto dto = (ContactPhoneDto) service.getDto(
                ComponentName.CONTACT_PHONE,
                id
        );

        return WebUtils.getResponseEntityForCachingDto(dto);
    }

    @GetMapping("/competent/locale/{localeName}/all")
    public ResponseEntity<Set<ContactPhoneDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllContactPhoneDtos(localeName));
    }

    @PutMapping("/competent/locale/{localeName}")
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestBody UpdateContactPhoneRequest request
    ) {
        service.update(
                ComponentName.CONTACT_PHONE,
                null,
                request,
                PageName.CONTACT,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/competent/id/{id}/locale/{localeName}")
    public ResponseEntity<Void> delete(
            @PathVariable int id,
            @PathVariable String localeName
    ) {
        service.delete(
                ComponentName.CONTACT_PHONE,
                id,
                PageName.CONTACT,
                localeName
        );

        return ResponseEntity.ok().build();
    }
}
