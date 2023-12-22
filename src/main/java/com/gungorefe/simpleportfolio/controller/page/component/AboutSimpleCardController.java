package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.AboutSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/about/component/simple-card")
@RestController
public class AboutSimpleCardController {
    private final ComponentService service;

    @PostMapping("/competent/competent/locale/{localeName}")
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestPart CreateAboutSimpleCardRequest request,
            @RequestPart MultipartFile image
    ) {
        service.create(
                ComponentName.ABOUT_SIMPLE_CARD,
                image,
                request,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AboutSimpleCardDto> getDto(@PathVariable int id) {
        return ResponseEntity.ok((AboutSimpleCardDto) service.getDto(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        ));
    }

    @GetMapping("/competent/competent/locale/{localeName}/all")
    public ResponseEntity<Set<AboutSimpleCardDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllAboutSimpleCardDtos(localeName));
    }

    @GetMapping(value = "/id/{id}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable int id,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                ComponentName.ABOUT_SIMPLE_CARD,
                id,
                imageName
        );

        return ResponseEntity.ok()
                .contentType(image.mimeType())
                .body(image.bytes());
    }

    @PutMapping(
            value = "/competent/competent",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> update(
            @RequestPart UpdateAboutSimpleCardRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.ABOUT_SIMPLE_CARD,
                image,
                request
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/competent/competent/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        );

        return ResponseEntity.ok().build();
    }
}
