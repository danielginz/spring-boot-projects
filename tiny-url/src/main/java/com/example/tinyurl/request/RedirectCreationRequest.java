package com.example.tinyurl.request;

import jakarta.annotation.Nonnull;


public class RedirectCreationRequest {
	@Nonnull
    private String alias;
    @Nonnull
    private String url;

    public RedirectCreationRequest(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}