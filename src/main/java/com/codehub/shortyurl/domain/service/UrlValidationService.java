package com.codehub.shortyurl.domain.service;

import com.codehub.shortyurl.domain.exception.ShortUrlException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class UrlValidationService {
    public static boolean validateUrl(String url) {
        try {
            URI uri = new URI(url);
            return Optional.ofNullable(uri.getScheme()).isPresent() &&
                    Optional.ofNullable(uri.getHost()).isPresent();
        } catch (URISyntaxException e) {
            throw new ShortUrlException("Error occurs when validating url", e);
        }
    }
}
