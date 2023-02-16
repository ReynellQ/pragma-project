package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.FoodPlateRegisterDTO;
import com.pragma.foodcourtservice.application.dto.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implementation of the handler  interface to communicate FoodPlateService and FoodPlateServiceController. This uses
 * the food plate service port and the mapper to FoodPlateDto (the input of the API) to FoodPlate (input of model
 * layer).
 */
@Service
@Transactional
public class FoodPlateHandler implements IFoodPlateHandler {
    private final IFoodPlateServicePort platoServicePort;
    private final FoodPlateDtoMapper foodPlateDtoMapper;

    public FoodPlateHandler(IFoodPlateServicePort platoServicePort, FoodPlateDtoMapper foodPlateDtoMapper) {
        this.platoServicePort = platoServicePort;
        this.foodPlateDtoMapper = foodPlateDtoMapper;
    }
    /**
     * Saves the data of a food plate in the application. Map the data of the food plate to register and calls the
     * service in charge of save the food plate.
     * @param foodPlate the DTO with the data of the food plate to register.
     */
    @Override
    public void saveFoodPlate(FoodPlateRegisterDTO foodPlate) {
        FoodPlate p = foodPlateDtoMapper.toPlatoFromSave(foodPlate);
        p.setActive(true);
        platoServicePort.savePlato(p);
    }
    /**
     * Updates and saves the data of a food plate in the application. Map the data of the food plate to register and
     * calls the service in charge of update the food plate.
     * @param foodPlate the DTO with the data of the food plate to update.
     */
    @Override
    public void updateFoodPlate(FoodPlateUpdateDto foodPlate) {
        platoServicePort.updatePlato(foodPlateDtoMapper.toPlatoFromUpdate(foodPlate));
    }
}
