package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.PageName;
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
            value = "/competent/locale/{localeName}",
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
                localeName,
                PageName.HOME
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HomeSimpleCardDto> getDto(@PathVariable int id) {
        HomeSimpleCardDto dto = (HomeSimpleCardDto) service.getDto(
                ComponentName.HOME_SIMPLE_CARD,
                id
        );

        return WebUtils.getResponseEntityForCachingDto(dto);
    }

    @GetMapping("/competent/locale/{localeName}/all")
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

        return WebUtils.getResponseEntityForCachingImage(image);

    }

    @PutMapping(
            value = "/competent/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestPart UpdateHomeSimpleCardRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.HOME_SIMPLE_CARD,
                image,
                request,
                PageName.HOME,
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
                ComponentName.HOME_SIMPLE_CARD,
                id,
                PageName.HOME,
                localeName
        );

        return ResponseEntity.ok().build();
    }
}
