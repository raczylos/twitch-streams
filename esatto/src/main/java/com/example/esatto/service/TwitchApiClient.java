package com.example.esatto.service;

import com.example.esatto.response.GameResponse;
import com.example.esatto.response.StreamResponse;
import com.example.esatto.response.TwitchApiResponse;
import com.example.esatto.response.TwitchTokensResponse;
import com.example.esatto.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class TwitchApiClient {
    private static final String TWITCH_API_BASE_URL = "https://api.twitch.tv/helix";

    private final RestTemplate restTemplate;

    private final String twitchClientId;

    private final String twitchClientSecret;

    private String appAccessToken;

    public TwitchApiClient(RestTemplateBuilder restTemplateBuilder,
                           @Value("${twitch.client-id}") String clientId,
                           @Value("${twitch.client-secret}") String clientSecret) {
        this.restTemplate = restTemplateBuilder.build();
        this.twitchClientId = clientId;
        this.twitchClientSecret = clientSecret;
        this.appAccessToken = this.getTwitchTokens().accessToken();
    }


    public TwitchTokensResponse getTwitchTokens() {
        String twitchTokenUrl = "https://id.twitch.tv/oauth2/token";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(twitchTokenUrl)
                .queryParam("client_id", twitchClientId)
                .queryParam("client_secret", twitchClientSecret)
                .queryParam("grant_type", "client_credentials");


        ResponseEntity<TwitchTokensResponse> twitchUserResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                null,
                TwitchTokensResponse.class
        );
        return twitchUserResponse.getBody();
    }

    public TwitchApiResponse<StreamResponse> fetchLiveStreams(String cursor) {
        HttpHeaders headers = createHeaders();
        String url = TWITCH_API_BASE_URL + "/streams";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("first", 100);
        if(cursor != null){
            builder.queryParam("after", cursor);
        }
        ResponseEntity<TwitchApiResponse<StreamResponse>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public Optional<UserResponse> fetchStreamer(String userId) {
        HttpHeaders headers = createHeaders();
        String url = TWITCH_API_BASE_URL + "/users?id=" + userId;

        ResponseEntity<TwitchApiResponse<UserResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody().getData().stream().findFirst();
    }

    public Optional<GameResponse> fetchGame(String gameId) {
        HttpHeaders headers = createHeaders();
        String url = TWITCH_API_BASE_URL + "/games?id=" + gameId;

        ResponseEntity<TwitchApiResponse<GameResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody().getData().stream().findFirst();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", twitchClientId);
        headers.set("Authorization", "Bearer " + appAccessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
