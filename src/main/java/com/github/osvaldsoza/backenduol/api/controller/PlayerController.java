package com.github.osvaldsoza.backenduol.api.controller;

import com.github.osvaldsoza.backenduol.domain.dto.CreatePlayerDTO;
import com.github.osvaldsoza.backenduol.domain.dto.ListPlayerDTO;
import com.github.osvaldsoza.backenduol.domain.expetions.NoCodiNameAvailable;
import com.github.osvaldsoza.backenduol.domain.model.Player;
import com.github.osvaldsoza.backenduol.domain.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePlayerDTO createPlayerDTO) {
        try {
            playerService.create(createPlayerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Player created successfully!");
        } catch (NoCodiNameAvailable e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ListPlayerDTO> list(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int size) {
        return playerService.list(page, size);
    }
}
