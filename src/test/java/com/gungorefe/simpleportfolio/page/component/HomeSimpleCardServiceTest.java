package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
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
public class HomeSimpleCardServiceTest {
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ImageService imageService;
    private final String imageName = "image1.jpg";
    private final int id = 1;
    private final PageName pageName = PageName.HOME;
    private final ComponentName componentName = ComponentName.HOME_SIMPLE_CARD;
    private final String localeName = LocaleName.TURKISH.value;

    @Test
    public void givenHomeSimpleCardImageNameAndId1_shouldReturnHomeSimpleCardDto() {
        ComponentDto dto = componentService.getDto(
                ComponentName.HOME_SIMPLE_CARD,
                id
        );

        assertEquals(dto.getClass(), HomeSimpleCardDto.class);
    }

    @Test
    public void givenCreateHomeSimpleCardRequestWithMultipartFile_shouldCreateHomeCarouselImage() {
        CreateHomeSimpleCardRequest request = new CreateHomeSimpleCardRequest(
                "created title",
                "created text",
                1
        );
        Image image = imageService.get(imageName);
        MultipartFile imageFile = new MockMultipartFile(
                imageName,
                imageName,
                MediaType.IMAGE_JPEG_VALUE,
                image.bytes()
        );
        HomeSimpleCard homeSimpleCard = (HomeSimpleCard) componentService.create(
                componentName,
                imageFile,
                request,
                localeName,
                pageName
        );

        assertEquals(request.title(), homeSimpleCard.getTitle());
        assertEquals(request.text(), homeSimpleCard.getText());
        assertEquals(request.displayOrder(), homeSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenUpdateHomeSimpleCardRequest_shouldUpdateHomeSimpleCard() {
        UpdateHomeSimpleCardRequest request = new UpdateHomeSimpleCardRequest(
                id,
                "updated title",
                "updated text",
                8
        );
        HomeSimpleCard homeSimpleCard = (HomeSimpleCard) componentService.update(
                componentName,
                null,
                request,
                pageName,
                localeName
        );

        assertEquals(request.title(), homeSimpleCard.getTitle());
        assertEquals(request.text(), homeSimpleCard.getText());
        assertEquals(request.displayOrder(), homeSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenHomeSimpleCardId_shouldDeleteHomeSimpleCard() {
        assertDoesNotThrow(() -> {
            componentService.delete(
                    componentName,
                    id,
                    pageName,
                    localeName
            );
        });
        assertThrowsExactly(
                FileNotFoundException.class,
                () -> imageService.get(this.imageName)
        );
    }
}
