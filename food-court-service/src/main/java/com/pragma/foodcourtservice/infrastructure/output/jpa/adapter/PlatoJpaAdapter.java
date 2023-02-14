package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Platos;
import com.pragma.foodcourtservice.domain.spi.IPlatoPersistencePort;
import com.pragma.foodcourtservice.infrastructure.exception.PlatoDoesntExists;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.PlatosEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.PlatoEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IPlatoRepository;

import java.util.Optional;

public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final PlatoEntityMapper platoEntityMapper;

    public PlatoJpaAdapter(IPlatoRepository platoRepository, PlatoEntityMapper platoEntityMapper) {
        this.platoRepository = platoRepository;
        this.platoEntityMapper = platoEntityMapper;
    }

    @Override
    public void savePlato(Platos plato) {
        platoRepository.save(platoEntityMapper.toEntity(plato));
    }

    @Override
    public Platos updatePlato(Platos platoBase) {
        platoRepository.save(platoEntityMapper.toEntity(platoBase));
        return platoBase;
    }

    @Override
    public Platos getPlato(Long id) {
        Optional<PlatosEntity> entityOptional = platoRepository.findById(id);
        if(entityOptional.isEmpty())
            throw new PlatoDoesntExists();
        return platoEntityMapper.toPlato(entityOptional.get());
    }
}
