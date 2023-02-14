package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.PlatosSaveDTO;
import com.pragma.foodcourtservice.application.dto.PlatosUpdateDTO;

public interface IPlatosHandler {
    void savePlato(PlatosSaveDTO plato);
    void updatePlato(PlatosUpdateDTO platoBase);
}
