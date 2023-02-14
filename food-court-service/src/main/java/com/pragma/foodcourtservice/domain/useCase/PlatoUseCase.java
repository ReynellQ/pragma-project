package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.domain.api.IPlatoServicePort;
import com.pragma.foodcourtservice.domain.api.IPlatoValidator;
import com.pragma.foodcourtservice.domain.exception.BadData;
import com.pragma.foodcourtservice.domain.exception.ForbiddenUpdate;
import com.pragma.foodcourtservice.domain.model.Platos;
import com.pragma.foodcourtservice.domain.spi.IPlatoPersistencePort;

public class PlatoUseCase implements IPlatoServicePort {
    private final IPlatoPersistencePort platoPersistencePort;
    private final IPlatoValidator platoValidator;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort, IPlatoValidator platoValidator) {
        this.platoPersistencePort = platoPersistencePort;
        this.platoValidator = platoValidator;
    }

    @Override
    public void savePlato(Platos plato) {
        if(!platoValidator.validatesIdRestaurant(plato.getIdRestaurante()))
            throw new BadData();
        if(!platoValidator.validatesPrecio(plato.getPrecio()))
            throw new BadData();
        platoPersistencePort.savePlato(plato);
    }

    @Override
    public Platos updatePlato(Platos platoUpdated) {
        Platos platoToUpdate = platoPersistencePort.getPlato(platoUpdated.getId());
        //No puede cambiar el estado de activo
        if(!platoToUpdate.getActivo().equals(platoUpdated.getActivo()) && platoUpdated.getActivo()!=null)
            throw new ForbiddenUpdate();
        //No puede cambiar la categoria
        if(!platoToUpdate.getIdCategoria().equals(platoUpdated.getIdCategoria()) && platoUpdated.getIdCategoria() !=null)
            throw new ForbiddenUpdate();
        //No puede cambiar el restaurante
        if(!platoToUpdate.getIdRestaurante().equals(platoUpdated.getIdRestaurante()) && platoUpdated.getIdRestaurante()!=null)
            throw new ForbiddenUpdate();
        //Solo actualizamos la descripcion si no es nulo
        if(platoUpdated.getDescripcion() != null)
            platoToUpdate.setDescripcion(platoUpdated.getDescripcion());
        //Solo actualizamos el precio si no es nulo
        if(platoUpdated.getPrecio() != null)
            platoToUpdate.setPrecio(platoUpdated.getPrecio());
        return platoPersistencePort.updatePlato(platoToUpdate);
    }
}
