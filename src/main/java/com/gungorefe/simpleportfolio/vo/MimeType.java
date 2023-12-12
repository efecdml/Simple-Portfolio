package com.gungorefe.simpleportfolio.vo;

import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public enum MimeType {
    IMAGE_SVG_XML("image/svg+xml", new MediaType("image", "svg+xml")),
    WEBP("image/webp", new MediaType("image", "webp")),
    GIF("image/gif", MediaType.IMAGE_GIF),
    JPEG("image/jpeg", MediaType.IMAGE_JPEG),
    PNG("image/png", MediaType.IMAGE_PNG);
    public final String label;
    public final MediaType value;

    MimeType(String label, MediaType value) {
        this.label = label;
        this.value = value;
    }

    private static final Map<String, MediaType> VALUES = new HashMap<>();

    static {
        for (MimeType m : values()) {
            VALUES.put(m.label, m.value);
        }
    }

    public static MediaType get(String label) {
        MediaType mediaType = VALUES.get(label);

        if (mediaType == null) {
            throw ExceptionFactory.getMalformedImageMimeTypeException(label);
        }

        return mediaType;
    }

    public static void validate(String label) {
        if (!VALUES.containsKey(label)) {
            throw ExceptionFactory.getUnacceptableImageMimeTypeException(label);
        }
    }
}
