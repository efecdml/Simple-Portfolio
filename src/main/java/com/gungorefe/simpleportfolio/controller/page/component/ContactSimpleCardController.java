package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.ContactSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/contact/component/simple-card")
@RestController
public class ContactSimpleCardController {
    private final ComponentService service;

    @PostMapping(
            value = "/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestPart CreateContactSimpleCardRequest request,
            @RequestPart MultipartFile image
    ) {
        service.create(
                ComponentName.CONTACT_SIMPLE_CARD,
                image,
                request,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ContactSimpleCardDto> getDto(@PathVariable int id) {
        return ResponseEntity.ok((ContactSimpleCardDto) service.getDto(
                ComponentName.CONTACT_SIMPLE_CARD,
                id
        ));
    }

    @GetMapping("/locale/{localeName}/all")
    public ResponseEntity<Set<ContactSimpleCardDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllContactSimpleCardDtos(localeName));
    }

    @GetMapping(value = "/id/{id}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable int id,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                ComponentName.CONTACT_SIMPLE_CARD,
                id,
                imageName
        );

        return ResponseEntity.ok()
                .contentType(image.mimeType())
                .body(image.bytes());

    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(
            @RequestPart UpdateContactSimpleCardRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.CONTACT_SIMPLE_CARD,
                image,
                request
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(
                ComponentName.CONTACT_SIMPLE_CARD,
                id
        );

        return ResponseEntity.ok().build();
    }
}
