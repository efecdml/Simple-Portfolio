package com.gungorefe.simpleportfolio.controller.page;

import com.gungorefe.simpleportfolio.dto.page.ContactDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateContactRequest;
import com.gungorefe.simpleportfolio.service.page.PageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/contact")
@RestController
public class ContactController {
    private final PageService service;

    @GetMapping("/locale/{localeName}")
    public ResponseEntity<ContactDto> getDto(@PathVariable String localeName) {
        return ResponseEntity.ok((ContactDto) service.getDto(
                localeName,
                PageName.CONTACT
        ));
    }

    @PutMapping("/locale/{localeName}")
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestBody UpdateContactRequest request
    ) {
        service.update(
                localeName,
                PageName.CONTACT,
                request,
                null
        );

        return ResponseEntity.ok().build();
    }
}
