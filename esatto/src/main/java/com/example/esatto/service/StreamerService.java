package com.example.esatto.service;

import com.example.esatto.dto.StreamerDto;
import com.example.esatto.entity.StreamerEntity;
import com.example.esatto.mapper.StreamerMapper;
import com.example.esatto.repository.StreamerRepository;
import com.example.esatto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamerService {
    private final TwitchApiClient twitchApiClient;
    private final StreamerRepository streamerRepository;
    private final StreamerMapper streamerMapper;


    public StreamerDto getStreamer(String streamerId) {
        Optional<StreamerEntity> streamer =  streamerRepository.findByTwitchUserId(streamerId);
        if(streamer.isPresent()) {
            return streamerMapper.entityToStreamerDto(streamer.get());
        } else {
            try {
                return addStreamer(streamerId);
            } catch (RuntimeException e) {
                return null;
            }

        }
    }

    public StreamerDto addStreamer(String streamerId) {
        UserResponse userData = twitchApiClient.fetchStreamer(streamerId)
                .orElseThrow(() -> new RuntimeException("Streamer not found on Twitch"));

        StreamerEntity newStreamer = StreamerEntity.builder()
                .twitchUserId(userData.id())
                .login(userData.login())
                .displayName(userData.displayName())
                .profileImageUrl(userData.profileImageUrl())
                .build();

        streamerRepository.save(newStreamer);
        return streamerMapper.entityToStreamerDto(newStreamer);
    }


}
