package com.example.esatto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "streamer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreamerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "twitch_user_id", unique = true, nullable = false)
    private String twitchUserId;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "profile_image_url", columnDefinition = "TEXT")
    private String profileImageUrl;

    @OneToMany(mappedBy = "streamer")
    private List<StreamEntity> streams = new ArrayList<>();
}
