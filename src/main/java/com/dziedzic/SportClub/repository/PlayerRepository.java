package com.dziedzic.SportClub.repository;


import com.dziedzic.SportClub.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
