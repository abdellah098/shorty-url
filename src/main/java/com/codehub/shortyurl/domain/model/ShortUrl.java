package com.codehub.shortyurl.domain.model;

import com.codehub.shortyurl.domain.service.HashEncodingService;
import com.codehub.shortyurl.domain.service.UrlHashingService;
import com.codehub.shortyurl.domain.service.UrlValidationService;

import java.time.ZonedDateTime;
public class ShortUrl {
    private String longUrl;
    private String hashedUrl;
    private String shortUrlCode;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime expiresAt;

    private ShortUrl(Builder builder) {
        this.longUrl = builder.longUrl;
        this.hashedUrl = builder.hashedUrl;
        this.shortUrlCode = builder.shortUrlCode;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.expiresAt = builder.expiresAt;
    }

    public static  class Builder {
       private static final int SHORT_URL_CODE_LENGTH = 8;
        private String longUrl;
        private String hashedUrl;
        private String shortUrlCode;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private ZonedDateTime expiresAt;

        public Builder longUrl(String longUrl) {
            UrlValidationService.validateUrl(longUrl);

            this.longUrl = longUrl;
            String urlSha256Hash = UrlHashingService.generateUrlSha256Hash(longUrl);
            String encodedUrl = HashEncodingService.encodeToBase64(urlSha256Hash);
            this.hashedUrl = encodedUrl;

            // the first 8 characters represent short url code
            this.shortUrlCode = encodedUrl.substring(0, SHORT_URL_CODE_LENGTH);
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder expiresAt(ZonedDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public ShortUrl build() {
            return new ShortUrl(this);
        }
    }
}



