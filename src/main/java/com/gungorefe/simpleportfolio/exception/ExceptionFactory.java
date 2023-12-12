package com.gungorefe.simpleportfolio.exception;

import com.gungorefe.simpleportfolio.exception.image.MalformedImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageNameException;

public class ExceptionFactory {
    public static MalformedImageMimeTypeException getMalformedImageMimeTypeException(String mimeType) {
        return new MalformedImageMimeTypeException("Image could not be processed due to unsupported MIME type: " + mimeType);
    }

    public static UnacceptableImageMimeTypeException getUnacceptableImageMimeTypeException(String mimeType) {
        return new UnacceptableImageMimeTypeException("Unacceptable MIME type: " + mimeType);
    }

    public static UnacceptableImageNameException getUnacceptableImageNameException(String imageName) {
        return new UnacceptableImageNameException("Unacceptable image name: " + imageName);
    }
}
