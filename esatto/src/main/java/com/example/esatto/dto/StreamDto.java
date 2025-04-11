package com.example.esatto.dto;


import java.time.LocalDateTime;

public record StreamDto(
        Integer id,
        String twitchStreamId,
        String title,
        Integer viewerCount,
        LocalDateTime startedAt,
        String thumbnailUrl,
        String language,
        StreamerDto streamer,
        GameDto game
) {

}
