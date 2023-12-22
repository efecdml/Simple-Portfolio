package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/home/component/carousel-section")
@RestController
public class HomeCarouselSectionController {
    private final ComponentService service;

    @PostMapping(
            value = "/competent/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestPart CreateHomeCarouselSectionRequest request,
            @RequestPart MultipartFile image
    ) {
        service.create(
                ComponentName.HOME_CAROUSEL_SECTION,
                image,
                request,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HomeCarouselSectionDto> getDto(@PathVariable int id) {
        HomeCarouselSectionDto dto = (HomeCarouselSectionDto) service.getDto(
                ComponentName.HOME_CAROUSEL_SECTION,
                id
        );

        return WebUtils.getResponseEntityForCachingDto(dto);
    }

    @GetMapping("/competent/locale/{localeName}/all")
    public ResponseEntity<Set<HomeCarouselSectionDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllHomeCarouselSectionDtos(localeName));
    }

    @GetMapping(value = "/id/{id}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable int id,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                ComponentName.HOME_CAROUSEL_SECTION,
                id,
                imageName
        );

        return WebUtils.getResponseEntityForCachingImage(image);
    }

    @PutMapping(
            value = "/competent",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> update(
            @RequestPart UpdateHomeCarouselSectionRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.HOME_CAROUSEL_SECTION,
                image,
                request
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/competent/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(
                ComponentName.HOME_CAROUSEL_SECTION,
                id
        );

        return ResponseEntity.ok().build();
    }
}
