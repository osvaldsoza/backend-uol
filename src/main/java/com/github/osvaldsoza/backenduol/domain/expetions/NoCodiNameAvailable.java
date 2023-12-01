package com.github.osvaldsoza.backenduol.domain.expetions;

public class NoCodiNameAvailable extends RuntimeException{

    public NoCodiNameAvailable(String message){
        super(message);
    }
}
