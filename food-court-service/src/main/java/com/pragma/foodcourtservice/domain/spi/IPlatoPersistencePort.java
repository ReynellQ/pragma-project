package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Platos;

public interface IPlatoPersistencePort {
    void savePlato(Platos plato);
    Platos updatePlato(Platos platoBase);
    Platos getPlato(Long id);

}
