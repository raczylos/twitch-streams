package com.example.esatto.service;

import com.example.esatto.dto.GameDto;
import com.example.esatto.entity.GameEntity;
import com.example.esatto.mapper.GameMapper;
import com.example.esatto.repository.GameRepository;
import com.example.esatto.response.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final TwitchApiClient twitchApiClient;
    private final GameMapper gameMapper;

    public GameDto getGame(String twitchGameId) {
        Optional<GameEntity> game = gameRepository.findByTwitchGameId(twitchGameId);
        if(game.isPresent()) {
            return gameMapper.entityToGameDto(game.get());
        } else {
            try {
                return addGameFromTwitchApi(twitchGameId);
            } catch (RuntimeException e) {
                return null;
            }
        }
    }

    public GameDto addGameFromTwitchApi(String twitchGameId) {
        GameResponse gameData = twitchApiClient.fetchGame(twitchGameId)
                .orElseThrow(() -> new RuntimeException("Game not found on Twitch"));
        GameEntity newGame = GameEntity.builder()
                .twitchGameId(gameData.id())
                .name(gameData.name())
                .boxArtUrl(gameData.boxArtUrl())
                .build();
        gameRepository.save(newGame);

        return gameMapper.entityToGameDto(newGame);
    }

    public GameDto getGameByName(String name) {
        Optional<GameEntity> game = gameRepository.findByName(name);
        return game.map(gameMapper::entityToGameDto).orElse(null);

    }

}
