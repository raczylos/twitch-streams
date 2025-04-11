package com.example.esatto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TwitchTokensResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        @JsonProperty("expires_in") Integer expiresIn
) {

}
