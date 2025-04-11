package com.example.esatto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("id") String id,
        @JsonProperty("login") String login,
        @JsonProperty("display_name") String displayName,
        @JsonProperty("type") String type,
        @JsonProperty("broadcaster_type") String broadcasterType,
        @JsonProperty("description") String description,
        @JsonProperty("profile_image_url") String profileImageUrl,
        @JsonProperty("offline_image_url") String offlineImageUrl,
        @JsonProperty("view_count") int viewCount,
        @JsonProperty("email") String email,
        @JsonProperty("created_at") String createdAt
) {}
