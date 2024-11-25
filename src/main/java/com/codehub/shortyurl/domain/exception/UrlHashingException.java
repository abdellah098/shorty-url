package com.codehub.shortyurl.domain.exception;

public class UrlHashingException extends ShortUrlException {
    public UrlHashingException(String message) {
        super(message);
    }

    public UrlHashingException(String message, Throwable cause) {
        super(message, cause);
    }
}
