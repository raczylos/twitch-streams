package com.example.esatto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("box_art_url") String boxArtUrl
) { }
