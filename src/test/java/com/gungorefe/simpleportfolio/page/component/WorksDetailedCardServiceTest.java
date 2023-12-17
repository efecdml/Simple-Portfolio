package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.WorksDetailedCardDto;
import com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard;
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
@Sql(scripts = {"/works-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class WorksDetailedCardServiceTest {
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ImageService imageService;
    private final String imageName = "image1.jpg";
    private final int id = 1;

    @Test
    public void givenWorksDetailedCardImageNameAndId1_shouldReturnWorksDetailedCardDto() {
        ComponentDto dto = componentService.getDto(
                ComponentName.WORKS_DETAILED_CARD,
                id
        );

        assertEquals(dto.getClass(), WorksDetailedCardDto.class);
    }

    @Test
    public void givenCreateWorksDetailedCardRequestWithMultipartFile_shouldCreateWorksDetailedCard() {
        CreateWorksDetailedCardRequest request = new CreateWorksDetailedCardRequest(
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
        WorksDetailedCard worksDetailedCard = (WorksDetailedCard) componentService.create(
                ComponentName.WORKS_DETAILED_CARD,
                imageFile,
                request,
                LocaleName.TURKISH.value
        );

        assertEquals(request.title(), worksDetailedCard.getTitle());
        assertEquals(request.text(), worksDetailedCard.getText());
        assertEquals(request.displayOrder(), worksDetailedCard.getDisplayOrder());
    }

    @Test
    public void givenUpdateWorksDetailedCardRequest_shouldUpdateWorksDetailedCard() {
        UpdateWorksDetailedCardRequest request = new UpdateWorksDetailedCardRequest(
                id,
                "updated title",
                "updated text",
                8
        );
        WorksDetailedCard worksDetailedCard = (WorksDetailedCard) componentService.update(
                ComponentName.WORKS_DETAILED_CARD,
                null,
                request
        );

        assertEquals(request.title(), worksDetailedCard.getTitle());
        assertEquals(request.text(), worksDetailedCard.getText());
        assertEquals(request.displayOrder(), worksDetailedCard.getDisplayOrder());
    }

    @Test
    public void givenWorksDetailedCardId_shouldDeleteWorksDetailedCard() {
        assertDoesNotThrow(() -> {
            componentService.delete(
                    ComponentName.WORKS_DETAILED_CARD,
                    id
            );
        });
        assertThrowsExactly(
                FileNotFoundException.class,
                () -> imageService.get(this.imageName)
        );
    }
}
