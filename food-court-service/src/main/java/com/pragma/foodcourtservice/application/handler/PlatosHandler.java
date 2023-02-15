package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.PlatosSaveDTO;
import com.pragma.foodcourtservice.application.dto.PlatosUpdateDTO;
import com.pragma.foodcourtservice.application.mapper.PlatosDTOMapper;
import com.pragma.foodcourtservice.domain.api.IPlatoServicePort;
import com.pragma.foodcourtservice.domain.model.Platos;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlatosHandler implements IPlatosHandler{
    private final IPlatoServicePort platoServicePort;
    private final PlatosDTOMapper platosDTOMapper;

    public PlatosHandler(IPlatoServicePort platoServicePort, PlatosDTOMapper platosDTOMapper) {
        this.platoServicePort = platoServicePort;
        this.platosDTOMapper = platosDTOMapper;
    }

    @Override
    public void savePlato(PlatosSaveDTO plato) {
        Platos p = platosDTOMapper.toPlatoFromSave(plato);
        p.setActivo(true);
        platoServicePort.savePlato(p);
    }

    @Override
    public void updatePlato(PlatosUpdateDTO platoBase) {
        platoServicePort.updatePlato(platosDTOMapper.toPlatoFromUpdate(platoBase));
    }
}
