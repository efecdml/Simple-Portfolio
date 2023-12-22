package com.gungorefe.simpleportfolio.controller.page;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.dto.page.WorksDto;
import com.gungorefe.simpleportfolio.service.page.PageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/works")
@RestController
public class WorksController {
    private final PageService service;

    @GetMapping("/locale/{localeName}")
    public ResponseEntity<WorksDto> getDto(@PathVariable String localeName) {
        return ResponseEntity.ok((WorksDto) service.getDto(
                localeName,
                PageName.WORKS
        ));
    }

    @GetMapping(value = "/locale/{localeName}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable String localeName,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                PageName.WORKS,
                localeName,
                imageName
        );

        return ResponseEntity.ok()
                .contentType(image.mimeType())
                .body(image.bytes());
    }

    @PutMapping(
            value = "/competent/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestPart UpdateWorksRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                localeName,
                PageName.WORKS,
                request,
                image
        );

        return ResponseEntity.ok().build();
    }
}
