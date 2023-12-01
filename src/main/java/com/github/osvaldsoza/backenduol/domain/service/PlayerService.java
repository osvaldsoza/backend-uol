package com.github.osvaldsoza.backenduol.domain.service;

import com.github.osvaldsoza.backenduol.domain.dto.CadastrarJogadorDTO;
import com.github.osvaldsoza.backenduol.domain.model.GrupType;
import com.github.osvaldsoza.backenduol.domain.model.Player;
import com.github.osvaldsoza.backenduol.domain.repository.JogadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    private Environment env;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CodiNomeService codiNomeService;


    public Player cadastrar(CadastrarJogadorDTO jogadorDTO) {
        Player player = modelMapper.map(jogadorDTO, Player.class);
        player.setCodiNome(getCodiNome(player.getGrupType()));
        return jogadorRepository.save(player);
    }

    private String getCodiNome(GrupType grupType){
        String codiNome = "";
        if(grupType == GrupType.VINGADORES){
             codiNome = codiNomeService.getAverngersCodiNomesList().stream().findFirst().orElseThrow();
            codiNomeService.getJusticeLeagueCodiNomesList().remove(codiNome);
        }

        codiNome = codiNomeService.getJusticeLeagueCodiNomesList().stream().findFirst().orElseThrow();
        codiNomeService.getJusticeLeagueCodiNomesList().remove(codiNome);

        return codiNome;
    }
}
