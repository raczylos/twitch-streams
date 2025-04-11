package com.example.esatto.dto;

public record StreamerDto(
        Integer id,
        String twitchUserId,
        String login,
        String displayName,
        String profileImageUrl
) {

}
