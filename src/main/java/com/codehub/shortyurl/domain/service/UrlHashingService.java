package com.codehub.shortyurl.domain.service;

import com.codehub.shortyurl.domain.exception.UrlHashingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UrlHashingService {
    private static final String ALGORITHM = "SHA-256";

    public static String generateUrlSha256Hash(String url) {
        String checkedUrl = Optional.ofNullable(url).orElseThrow(() -> new UrlHashingException("url is null"));
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hashBytes = digest.digest(checkedUrl.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b)); // Convert to hex
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UrlHashingException("Error generating url hash", e);
        }
    }
}
