package com.gungorefe.simpleportfolio.controller.page;

import com.gungorefe.simpleportfolio.dto.page.HomeDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.service.page.PageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/pages/page/home")
@RestController
public class HomeController {
    private final PageService service;

    @GetMapping("/locale/{localeName}")
    public ResponseEntity<HomeDto> getDto(@PathVariable String localeName) {
        return ResponseEntity.ok((HomeDto) service.getDto(
                localeName,
                PageName.HOME
        ));
    }

    @PutMapping("/locale/{localeName}")
    public ResponseEntity<Void> update(
            @PathVariable String localeName,
            @RequestBody UpdateHomeRequest request
    ) {
        service.update(
                localeName,
                PageName.HOME,
                request,
                null
        );

        return ResponseEntity.ok().build();
    }
}
