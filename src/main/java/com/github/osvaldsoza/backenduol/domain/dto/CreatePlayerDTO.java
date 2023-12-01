package com.github.osvaldsoza.backenduol.domain.dto;

import com.github.osvaldsoza.backenduol.domain.model.GrupType;
import lombok.Data;

@Data
public class PlayerDTO {

    String name;
    String email;
    String phone;
    GrupType grupType;
}
