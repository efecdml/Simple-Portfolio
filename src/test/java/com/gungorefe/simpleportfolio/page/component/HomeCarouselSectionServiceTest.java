package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Sql(scripts = {"/home-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class HomeCarouselSectionServiceTest {
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ImageService imageService;
    private final String imageName = "image1.jpg";
    private final int id = 1;

    @Test
    public void givenHomeCarouselSectionImageNameAndId1_shouldReturnHomeCarouselSection() {
        ComponentDto dto = componentService.getDto(
                ComponentName.HOME_CAROUSEL_SECTION,
                id
        );

        assertEquals(dto.getClass(), HomeCarouselSectionDto.class);
    }

    @Test
    public void givenCreateHomeCarouselSectionRequestWithMultipartFile_shouldCreateHomeCarouselSection() {
        CreateHomeCarouselSectionRequest request = new CreateHomeCarouselSectionRequest(
                "new text",
                6
        );
        Image image = imageService.get(imageName);
        MultipartFile imageFile = new MockMultipartFile(
                imageName,
                imageName,
                MediaType.IMAGE_JPEG_VALUE,
                image.bytes()
        );
        HomeCarouselSection homeCarouselSection = (HomeCarouselSection) componentService.create(
                ComponentName.HOME_CAROUSEL_SECTION,
                imageFile,
                request,
                LocaleName.ENGLISH.value
        );

        assertEquals(request.text(), homeCarouselSection.getText());
        assertEquals(request.displayOrder(), homeCarouselSection.getDisplayOrder());
    }

    @Test
    public void givenUpdateHomeCarouselSectionRequest_shouldUpdateHomeCarouselSection() {
        UpdateHomeCarouselSectionRequest request = new UpdateHomeCarouselSectionRequest(
                id,
                "updated text",
                8
        );
        HomeCarouselSection homeCarouselSection = (HomeCarouselSection) componentService.update(
                ComponentName.HOME_CAROUSEL_SECTION,
                null,
                request
        );

        assertEquals(request.text(), homeCarouselSection.getText());
        assertEquals(request.displayOrder(), homeCarouselSection.getDisplayOrder());
    }

    @Test
    public void givenHomeCarouselSectionId_shouldDeleteHomeCarouselSection() {
        assertDoesNotThrow(() -> {
            componentService.delete(
                    ComponentName.HOME_CAROUSEL_SECTION,
                    id
            );
        });
        assertThrowsExactly(
                FileNotFoundException.class,
                () -> imageService.get(this.imageName)
        );
    }
}
