package com.pragma.foodcourtservice.infrastructure.thirdparty;

import com.pragma.foodcourtservice.domain.api.IRestauranteValidator;
import com.pragma.foodcourtservice.application.dto.PersonaDTO;
import org.springframework.web.client.RestTemplate;

public class RestauranteValidator implements IRestauranteValidator {
    private final RestTemplate userServiceConnection;


    public RestauranteValidator(RestTemplate userServiceConnection) {
        this.userServiceConnection = userServiceConnection;
    }

    @Override
    public boolean validateOwner(Long id) {
        PersonaDTO owner = userServiceConnection.getForObject("http://localhost:8080/persona/"+id, PersonaDTO.class);

        return owner.getRol() == 2;
    }

    @Override
    public boolean validatePhone(String phone) {
        if(phone.charAt(0) == '+')
            return validatePhone(phone.substring(1));
        return allNumeric(phone);
    }



    @Override
    public boolean validateName(String nombre) {
        return true;
    }
    private boolean allNumeric(String str){
        for(char c : str.toCharArray())
            if(!Character.isDigit(c))
                return false;
        return true;
    }
}
