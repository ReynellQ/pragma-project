package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Platos;

public interface IPlatoServicePort {
    void savePlato(Platos plato);
    Platos updatePlato(Platos platoBase);
}
