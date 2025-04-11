package com.example.esatto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record StreamResponse(
        @JsonProperty("id") String twitchStreamId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("user_login") String userLogin,
        @JsonProperty("user_name") String userName,
        @JsonProperty("game_id") String gameId,
        @JsonProperty("game_name") String gameName,
        @JsonProperty("type") String type,
        @JsonProperty("title") String title,
        @JsonProperty("tags") List<String> tags,
        @JsonProperty("viewer_count") Integer viewerCount,
        @JsonProperty("started_at") LocalDateTime startedAt,
        @JsonProperty("language") String language,
        @JsonProperty("thumbnail_url") String thumbnailUrl,
        @JsonProperty("tag_ids") List<String> tagIds,
        @JsonProperty("is_mature") Boolean isMature
) {}
