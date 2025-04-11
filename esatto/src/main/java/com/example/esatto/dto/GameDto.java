package com.example.esatto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameDto(
        String twitchGameId,
        String name,
        String boxArtUrl
) {

}
