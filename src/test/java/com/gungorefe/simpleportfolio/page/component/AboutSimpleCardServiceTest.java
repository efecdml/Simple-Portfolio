package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.AboutSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard;
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
@Sql(scripts = {"/about-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class AboutSimpleCardServiceTest {
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ImageService imageService;
    private final String imageName = "image1.jpg";
    private final int id = 1;

    @Test
    public void givenAboutSimpleCardImageNameAndId1_shouldReturnAboutSimpleCardDto() {
        ComponentDto dto = componentService.getDto(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        );

        assertEquals(dto.getClass(), AboutSimpleCardDto.class);
    }

    @Test
    public void givenCreateAboutSimpleCardRequestWithMultipartFile_shouldCreateAboutSimpleCard() {
        CreateAboutSimpleCardRequest request = new CreateAboutSimpleCardRequest(
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
        AboutSimpleCard aboutSimpleCard = (AboutSimpleCard) componentService.create(
                ComponentName.ABOUT_SIMPLE_CARD,
                imageFile,
                request,
                LocaleName.ENGLISH.value
        );

        assertEquals(request.title(), aboutSimpleCard.getTitle());
        assertEquals(request.text(), aboutSimpleCard.getText());
        assertEquals(request.displayOrder(), aboutSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenUpdateAboutSimpleCardRequest_shouldUpdateAboutSimpleCard() {
        UpdateAboutSimpleCardRequest request = new UpdateAboutSimpleCardRequest(
                id,
                "updated title",
                "updated text",
                8
        );
        AboutSimpleCard aboutSimpleCard = (AboutSimpleCard) componentService.update(
                ComponentName.ABOUT_SIMPLE_CARD,
                null,
                request
        );

        assertEquals(request.title(), aboutSimpleCard.getTitle());
        assertEquals(request.text(), aboutSimpleCard.getText());
        assertEquals(request.displayOrder(), aboutSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenAboutSimpleCardId_shouldDeleteAboutSimpleCard() {
        assertDoesNotThrow(() -> componentService.delete(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        ));
        assertThrowsExactly(FileNotFoundException.class,
                () -> imageService.get(this.imageName));
    }
}
