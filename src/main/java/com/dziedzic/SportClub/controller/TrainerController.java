package com.dziedzic.SportClub.controller;

import com.dziedzic.SportClub.model.Player;
import com.dziedzic.SportClub.model.Trainer;
import com.dziedzic.SportClub.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/trainer")
public class TrainerController {
    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Trainer> allTrainer() {
        return trainerRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> getTrainer(@PathVariable Long id) {
        return trainerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTrainer(@RequestBody Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Trainer> editTrainer(@RequestBody Trainer newTrainer, @PathVariable Long id) {
        return trainerRepository.findById(id)
                .map(trainer -> {
                    trainer.setName(newTrainer.getName());
                    trainer.setLastname(newTrainer.getLastname());
                    trainer.setAge(newTrainer.getAge());
                    return trainerRepository.save(trainer);
                });
    }
}
