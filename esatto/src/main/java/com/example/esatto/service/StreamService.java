package com.example.esatto.service;

import com.example.esatto.dto.GameDto;
import com.example.esatto.dto.StreamDto;
import com.example.esatto.dto.StreamerDto;
import com.example.esatto.entity.StreamEntity;
import com.example.esatto.mapper.GameMapper;
import com.example.esatto.mapper.StreamMapper;
import com.example.esatto.mapper.StreamerMapper;
import com.example.esatto.repository.StreamRepository;
import com.example.esatto.response.StreamResponse;
import com.example.esatto.response.TwitchApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamService {

    private final StreamRepository streamRepository;
    private final StreamMapper streamMapper;
    private final TwitchApiClient twitchApiClient;
    private final StreamerService streamerService;
    private final GameService gameService;
    private final StreamerMapper streamerMapper;
    private final GameMapper gameMapper;


    public Page<StreamDto> getAllStreams(Pageable pageable) {
        return streamRepository.findAll(pageable)
                .map(streamMapper::entityToStreamDto);
    }

    public Page<StreamDto> getStreamsByGame(String gameId, Pageable pageable) {
        return streamRepository.findByGameTwitchGameId(gameId, pageable)
                .map(streamMapper::entityToStreamDto);
    }



    public void addStreamsFromTwitch(){
        String cursor = null;
        int i = 0;
        final int MAX_REQUESTS = 3;
        do {
            TwitchApiResponse<StreamResponse> twitchStreams = twitchApiClient.fetchLiveStreams(cursor);
            cursor = twitchStreams.getPagination().getCursor();
            i++;
            for (StreamResponse twitchStream : twitchStreams.getData()) {
                StreamerDto streamer = streamerService.getStreamer(twitchStream.userId());
                GameDto game = gameService.getGame(twitchStream.gameId());
                if(streamer == null || game == null) {
                    continue;
                }
                Optional<StreamEntity> existingStream = streamRepository.findByTwitchStreamId(twitchStream.twitchStreamId());
                if(existingStream.isPresent()) {
                    StreamEntity existingStreamEntity = existingStream.get();
                    existingStreamEntity.setTitle(twitchStream.title());
                    existingStreamEntity.setViewerCount(twitchStream.viewerCount());
                    existingStreamEntity.setThumbnailUrl(twitchStream.thumbnailUrl());
                    existingStreamEntity.setStartedAt(twitchStream.startedAt());
                    existingStreamEntity.setLanguage(twitchStream.language());
                    streamRepository.save(existingStreamEntity);
                } else {
                    StreamEntity newStream = StreamEntity.builder()
                            .twitchStreamId(twitchStream.twitchStreamId())
                            .title(twitchStream.title())
                            .viewerCount(twitchStream.viewerCount())
                            .startedAt(twitchStream.startedAt())
                            .thumbnailUrl(twitchStream.thumbnailUrl())
                            .language(twitchStream.language())
                            .streamer(streamerMapper.streamerDtoToEntity(streamer))
                            .game(gameMapper.gameDtoToEntity(game))
                            .build();
                    streamRepository.save(newStream);
                }

            }
        } while(cursor != null && i < MAX_REQUESTS);

    }


}
