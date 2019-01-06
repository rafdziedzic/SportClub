package com.dziedzic.SportClub.controller;

import com.dziedzic.SportClub.model.Player;
import com.dziedzic.SportClub.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return players;
    }

    //zastosowanie lambda do obsługi kodu błędów, jeśli nie będzie playera o danym id to zostanie wyświetlona strona błędu
    //dostaniemy po prostu ładniejszą stronę błędu.
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getCity(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPlayer(@RequestBody Player player) {
        playerRepository.save(player);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Player> editPlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
        return playerRepository.findById(id)
                .map(player1 -> {
                    player1.setName(newPlayer.getName());
                    player1.setLastname(newPlayer.getLastname());
                    player1.setAge(newPlayer.getAge());
                    return playerRepository.save(player1);
                });
    }
}
