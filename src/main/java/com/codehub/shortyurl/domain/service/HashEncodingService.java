package com.codehub.shortyurl.domain.service;

import com.codehub.shortyurl.domain.exception.ShortUrlException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public class HashEncodingService {
    public static String encodeToBase64(String hash) {
        String nonNullHash = Optional.ofNullable(hash)
                .orElseThrow(() -> new ShortUrlException("The provided hash is null"));

        byte[] hashBytes = nonNullHash.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
