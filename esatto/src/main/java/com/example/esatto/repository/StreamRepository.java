package com.example.esatto.repository;

import com.example.esatto.entity.StreamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreamRepository extends JpaRepository<StreamEntity, Long> {
    Page<StreamEntity> findAll(Pageable pageable);
    Page<StreamEntity> findByGameTwitchGameId(String gameId, Pageable pageable);
    Page<StreamEntity> findByStreamerId(Integer streamer_id, Pageable pageable);

    Optional<StreamEntity> findByTwitchStreamId(String twitchStreamId);
}
