package com.github.osvaldsoza.backenduol.domain.service;

import com.github.osvaldsoza.backenduol.domain.dto.CreatePlayerDTO;
import com.github.osvaldsoza.backenduol.domain.dto.ListPlayerDTO;
import com.github.osvaldsoza.backenduol.domain.expetions.NoCodiNameAvailable;
import com.github.osvaldsoza.backenduol.domain.model.GrupType;
import com.github.osvaldsoza.backenduol.domain.model.Player;
import com.github.osvaldsoza.backenduol.domain.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CodiNameService codiNameService;

    public Player create(CreatePlayerDTO jogadorDTO) {
        Player player = modelMapper.map(jogadorDTO, Player.class);
        player.setCodiName(getCodiNome(player.getGrupType()));
        return playerRepository.save(player);
    }

    public Page<ListPlayerDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<ListPlayerDTO> foo = playerRepository.findAll(pageable)
                .stream()
                .map(player -> modelMapper.map(player, ListPlayerDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(foo, pageable, foo.size());
    }

    private String getCodiNome(GrupType grupType) {
        if (grupType == GrupType.AVENGERS) {

            if (codiNameService.getAverngersCodiNomesList().isEmpty()) {
                throw new NoCodiNameAvailable("Codiname avengers no available.");
            }
            String firstCodiNome = codiNameService.getAverngersCodiNomesList().stream().findFirst().orElseThrow();
            codiNameService.getAverngersCodiNomesList().remove(firstCodiNome);
            return firstCodiNome;
        }

        if (codiNameService.getJusticeLeagueCodiNomesList().isEmpty()) {
            throw new NoCodiNameAvailable("Codiname justice league no available.");
        }
        String firstCodiNome = codiNameService.getJusticeLeagueCodiNomesList().stream().findFirst().orElseThrow();
        codiNameService.getJusticeLeagueCodiNomesList().remove(firstCodiNome);
        return firstCodiNome;
    }
}
