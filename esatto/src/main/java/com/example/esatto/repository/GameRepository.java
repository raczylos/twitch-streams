package com.example.esatto.repository;

import com.example.esatto.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findByTwitchGameId(String twitchId);
    Optional<GameEntity> findByName(String name);

}
