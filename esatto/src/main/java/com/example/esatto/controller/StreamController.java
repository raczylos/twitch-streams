package com.example.esatto.controller;

import com.example.esatto.dto.StreamDto;
import com.example.esatto.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/streams")
@RequiredArgsConstructor
public class StreamController {

    public final StreamService streamService;

    @GetMapping
    public ResponseEntity<Page<StreamDto>> getAllStreams(Pageable pageable) {
        return ResponseEntity.ok(streamService.getAllStreams(pageable));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Page<StreamDto>> getStreamsByGame(@PathVariable String gameId, Pageable pageable) {
        return ResponseEntity.ok(streamService.getStreamsByGame(gameId, pageable));
    }

    @PostMapping()
    public void addStreamsFromTwitch(){
        streamService.addStreamsFromTwitch();
    }


}
