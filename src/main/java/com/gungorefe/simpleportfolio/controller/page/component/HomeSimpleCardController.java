package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/home/component/simple-card")
@RestController
public class HomeSimpleCardController {
    private final ComponentService service;

    @PostMapping(
            value = "/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestPart CreateHomeSimpleCardRequest request,
            @RequestPart MultipartFile image
    ) {
        service.create(
                ComponentName.HOME_SIMPLE_CARD,
                image,
                request,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HomeSimpleCardDto> getDto(@PathVariable int id) {
        return ResponseEntity.ok((HomeSimpleCardDto) service.getDto(
                ComponentName.HOME_SIMPLE_CARD,
                id
        ));
    }

    @GetMapping("/locale/{localeName}/all")
    public ResponseEntity<Set<HomeSimpleCardDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllHomeSimpleCardDtos(localeName));
    }

    @GetMapping(value = "/id/{id}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable int id,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                ComponentName.HOME_SIMPLE_CARD,
                id,
                imageName
        );

        return ResponseEntity.ok()
                .contentType(image.mimeType())
                .body(image.bytes());

    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(
            @RequestPart UpdateHomeSimpleCardRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.HOME_SIMPLE_CARD,
                image,
                request
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(
                ComponentName.HOME_SIMPLE_CARD,
                id
        );

        return ResponseEntity.ok().build();
    }
}
