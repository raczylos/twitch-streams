package com.example.esatto.controller;

import com.example.esatto.dto.GameDto;

import com.example.esatto.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    public final GameService gameService;

    @GetMapping()
    public ResponseEntity<GameDto> getStreamsByGame(@RequestParam String name) {
        return ResponseEntity.ok(gameService.getGameByName(name));
    }
}
