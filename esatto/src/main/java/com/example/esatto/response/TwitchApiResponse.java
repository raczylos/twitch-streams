package com.example.esatto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class TwitchApiResponse<T> {
    @JsonProperty("data")
    private List<T> data;

    @JsonProperty("pagination")
    private Pagination pagination;

    @Getter
    public static class Pagination {
        @JsonProperty("cursor")
        private String cursor;
    }
}
