package com.github.osvaldsoza.backenduol.domain.dto;

import com.github.osvaldsoza.backenduol.domain.model.GrupType;
import lombok.Data;

@Data
public class ListPlayerDTO {

    Long id;
    String name;
    String email;
    String phone;
    String codiName;
    GrupType grupType;
}
