package com.github.osvaldsoza.backenduol.api.controller;

import com.github.osvaldsoza.backenduol.domain.dto.CreatePlayerDTO;
import com.github.osvaldsoza.backenduol.domain.dto.ListPlayerDTO;
import com.github.osvaldsoza.backenduol.domain.expetions.NoCodiNameAvailable;
import com.github.osvaldsoza.backenduol.domain.model.Player;
import com.github.osvaldsoza.backenduol.domain.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerrController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePlayerDTO createPlayerDTO) {
        try{
        Player player = playerService.create(createPlayerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);

        }catch (NoCodiNameAvailable e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ListPlayerDTO>> list() {

            List<ListPlayerDTO> playerDTOList = playerService.players();
            return ResponseEntity.status(HttpStatus.CREATED).body(playerDTOList);


    }
}
