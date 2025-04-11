package com.example.esatto.repository;

import com.example.esatto.entity.StreamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StreamerRepository extends JpaRepository<StreamerEntity, Long> {
    Optional<StreamerEntity> findByTwitchUserId(String twitchId);
}
