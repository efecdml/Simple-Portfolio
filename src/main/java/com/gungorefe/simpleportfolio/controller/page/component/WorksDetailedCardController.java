package com.gungorefe.simpleportfolio.controller.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.CreateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.WorksDetailedCardDto;
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
@RequestMapping("/api/pages/page/works/component/detailed-card")
@RestController
public class WorksDetailedCardController {
    private final ComponentService service;

    @PostMapping(
            value = "/competent/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> create(
            @PathVariable String localeName,
            @RequestPart CreateWorksDetailedCardRequest request,
            @RequestPart MultipartFile image
    ) {
        service.create(
                ComponentName.WORKS_DETAILED_CARD,
                image,
                request,
                localeName,
                PageName.WORKS
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<WorksDetailedCardDto> getDto(@PathVariable int id) {
        WorksDetailedCardDto dto = (WorksDetailedCardDto) service.getDto(
                ComponentName.WORKS_DETAILED_CARD,
                id
        );

        return WebUtils.getResponseEntityForCachingDto(dto);
    }

    @GetMapping("/competent/locale/{localeName}/all")
    public ResponseEntity<Set<WorksDetailedCardDto>> getAllDtos(@PathVariable String localeName) {
        return ResponseEntity.ok(service.getAllWorksDetailedCardDtos(localeName));
    }

    @GetMapping(value = "/id/{id}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable int id,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                ComponentName.WORKS_DETAILED_CARD,
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
            @RequestPart UpdateWorksDetailedCardRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                ComponentName.WORKS_DETAILED_CARD,
                image,
                request,
                PageName.WORKS,
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
                ComponentName.WORKS_DETAILED_CARD,
                id,
                PageName.WORKS,
                localeName
        );

        return ResponseEntity.ok().build();
    }
}
