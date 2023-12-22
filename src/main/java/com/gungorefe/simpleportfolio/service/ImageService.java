package com.gungorefe.simpleportfolio.service;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.vo.MimeType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Log4j2
@Service
public class ImageService {
    private static final String DYNAMIC_RESOURCES_DIR = String.join(
            "/",
            System.getProperty("user.home"),
            "simple-portfolio",
            "dynamic-resources"
    );
    private final Tika tika = new Tika();

    static {
        File dir = new File(DYNAMIC_RESOURCES_DIR);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                log.error("Error creating the directory for dynamic resources.");
            }
        }
    }

    /* Validates MIME type of the file, regulates it and writes to disk. If current image name is not null deletes the
     * file named it. Returns the name of the file written to disk. */
    @SneakyThrows
    public String save(
            MultipartFile image,
            @Nullable String currentImageName
    ) {
        String newName;
        OutputStream os;

        validateMimeType(image);
        newName = regulateName(image);
        os = new BufferedOutputStream(new FileOutputStream(getPath(newName)));

        try {
            os.write(image.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        }

        if (currentImageName != null) {
            delete(currentImageName);
        }

        return newName;
    }

    /* Gets bytes, name and MIME type of the file with given name. Creates Image dto with these variables and returns
    it. */
    @SneakyThrows
    public Image get(String name) {
        File imageFile = new File(getPath(name));
        byte[] bytes;
        MediaType mimeType;
        InputStream is = new BufferedInputStream(new FileInputStream(imageFile));

        try {
            bytes = is.readAllBytes();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        }
        mimeType = getMimeType(bytes);

        Image image = new Image(
                imageFile.getName(),
                mimeType,
                bytes
        );

        return image;
    }

    public void delete(String name) {
        File image = new File(getPath(name));

        if (!image.exists()) {
            log.warn("Image does not exist: " + image.getPath());
        }

        image.delete();
    }

    private MediaType getMimeType(byte[] bytes) {
        String mimeType = tika.detect(bytes);

        return MimeType.get(mimeType);
    }

    @SneakyThrows
    private void validateMimeType(MultipartFile image) {
        String mimeType;
        byte[] bytes;

        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        }

        mimeType = tika.detect(bytes);
        MimeType.validate(mimeType);
    }

    /* Extracts name of the file and gets its bytes. If it is lesser or 5 and greater than 255 throws
     * UnacceptableImageNameException exception. Because accepted MIME types have at least 4 characters with a period.
     * And for Linux, a file's name must be at most 255 characters.
     *
     * If name has characters more than 236 reduces by 19 for to include LocalDateTime in milliseconds for ETag.
     *
     * If name has bizarre characters makes them human-readable, puts hyphen between spaces, converts to lowercase
     * and returns it. For example, the result will be "offne-die-tur-20231016t225302219.jpg" if the name is
     * "Öffne die Tür!.jpg". */
    private String regulateName(MultipartFile image) {
        String name = image.getOriginalFilename();
        byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
        String extension;
        String nameWithoutExtension;
        String nameWithoutTime;
        String nameWithTime;
        String nameAsNormalized;
        String nameToReturn;

        if (bytes.length < 5 ||
                bytes.length > 255) {
            throw ExceptionFactory.getUnacceptableImageNameException(name);
        }

        extension = FilenameUtils.getExtension(name);
        nameWithoutExtension = name.substring(0, name.length() - extension.length());
        nameWithoutTime = bytes.length > 236
                ? nameWithoutExtension.substring(0, nameWithoutExtension.length() - 19)
                : nameWithoutExtension;
        nameWithTime = nameWithoutTime + " " + LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        nameAsNormalized = Normalizer.normalize(nameWithTime, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}|\\p{P}| $", "")
                .replaceAll(" ", "-")
                .toLowerCase();
        nameToReturn = nameAsNormalized + "." + extension;

        return nameToReturn;
    }

    private String getPath(String name) {
        String nameToReturn = String.join(
                "/",
                DYNAMIC_RESOURCES_DIR,
                name
        );

        return nameToReturn;
    }
}
