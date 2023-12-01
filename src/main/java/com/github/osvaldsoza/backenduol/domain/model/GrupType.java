package com.github.osvaldsoza.backenduol.domain.model;

import lombok.Getter;
import lombok.Setter;

public enum Grupo {

    VINGADORES("Vingadores"),
    LIGA_DA_JUSTICA("Liga da Justiça");

    @Getter
    @Setter
    private String descricao;

     Grupo(String descricao){
        this.descricao = descricao;
    }

}
