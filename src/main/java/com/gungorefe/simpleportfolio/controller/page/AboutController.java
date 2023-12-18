package com.gungorefe.simpleportfolio.controller.page;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.AboutDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateAboutRequest;
import com.gungorefe.simpleportfolio.service.page.PageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/about")
@RestController
public class AboutController {
    private final PageService service;

    @GetMapping("/locale/{localeName}")
    public ResponseEntity<AboutDto> getDto(@PathVariable String localeName) {
        return ResponseEntity.ok((AboutDto) service.getDto(
                localeName,
                PageName.ABOUT
        ));
    }

    @GetMapping(value = "/locale/{localeName}/image/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable String localeName,
            @PathVariable String imageName
    ) {
        Image image = service.getImage(
                PageName.ABOUT,
                localeName,
                imageName
        );

        return ResponseEntity.ok()
                .contentType(image.mimeType())
                .body(image.bytes());
    }

    @PutMapping(
            value = "/locale/{localeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestPart UpdateAboutRequest request,
            @RequestPart(required = false) MultipartFile image
    ) {
        service.update(
                localeName,
                PageName.ABOUT,
                request,
                image
        );

        return ResponseEntity.ok().build();
    }
}
