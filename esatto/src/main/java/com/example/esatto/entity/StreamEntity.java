package com.example.esatto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "stream")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "twitch_stream_id", unique = true, nullable = false)
    private String twitchStreamId;

    @Column(nullable = false)
    private String title;

    @Column(name = "viewer_count")
    private Integer viewerCount;

    @Column(name = "started_at", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedAt;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "language")
    private String language;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "streamer_id")
    private StreamerEntity streamer;
}
