package com.gungorefe.simpleportfolio;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class ImageServiceTest {
    private final ImageService service = new ImageService();

    @Test
    public void givenTextFile_shouldThrowUnacceptableImageMimeTypeException() {
        MockMultipartFile file = new MockMultipartFile(
                "file.txt",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "This is my file's content.".getBytes()
        );

        assertThrowsExactly(UnacceptableImageMimeTypeException.class,
                () -> service.save(
                        file,
                        null
                ));
    }

    @Test
    public void givenImage_shouldWriteToDisk_thenDeleteImageWithGivenAsOldImageName_andReturnTheImageNameWrittenToDisk() {
        Image image = service.get("image1.jpg");
        MockMultipartFile imageFile = new MockMultipartFile(
                "my image name.jpg",
                "my image name.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                image.bytes()
        );
        String imageName = service.save(
                imageFile,
                "image1.jpg"
        );

        assertFalse(imageName.isEmpty());
    }
}
