package com.example.esatto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEntity {
    @Id
    @Column(name = "twitch_game_id")
    private String twitchGameId;

    @Column(nullable = false)
    private String name;

    @Column(name = "box_art_url", columnDefinition = "TEXT")
    private String boxArtUrl;

    @OneToMany(mappedBy = "game")
    private List<StreamEntity> streams;

}
