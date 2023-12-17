package com.gungorefe.simpleportfolio.exception;

import com.gungorefe.simpleportfolio.exception.image.ImageNotFoundException;
import com.gungorefe.simpleportfolio.exception.image.MalformedImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageNameException;
import com.gungorefe.simpleportfolio.exception.page.ComponentNotFoundException;
import com.gungorefe.simpleportfolio.exception.page.InvalidLocaleException;
import com.gungorefe.simpleportfolio.exception.page.PageNotFoundException;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.PageName;

import java.text.MessageFormat;

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

    public static InvalidLocaleException getInvalidLocaleException(String localeName) {
        return new InvalidLocaleException("Invalid locale: " + localeName);
    }

    public static PageNotFoundException getPageNotFoundException(PageName name) {
        return new PageNotFoundException("Page not found: " + name.value);
    }

    public static ComponentNotFoundException getComponentNotFoundException(
            ComponentName name,
            int id
    ) {
        return new ComponentNotFoundException(MessageFormat.format(
                "Page component named: {0} with ID: {1} could not found",
                name,
                id
        ));
    }

    public static ImageNotFoundException getImageNotFoundException(String imageName) {
        return new ImageNotFoundException("Image not found: " + imageName);
    }
}
