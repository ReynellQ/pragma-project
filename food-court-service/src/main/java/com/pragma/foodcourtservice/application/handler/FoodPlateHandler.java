package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateChangeState;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implementation of the handler  interface to communicate FoodPlateService and FoodPlateServiceController. This uses
 * the food plate service port and the mapper to FoodPlateDto (the input of the API) to FoodPlate (input of model
 * layer).
 */
@Service
@Transactional
@RequiredArgsConstructor
public class FoodPlateHandler implements IFoodPlateHandler {
    private final IFoodPlateServicePort platoServicePort;
    private final FoodPlateDtoMapper foodPlateDtoMapper;

    /**
     * Saves the data of a food plate in the application. Map the data of the register DTO to a food plate and calls the
     * service in charge of save the food plate.
     * @param email the email of the creator.
     * @param foodPlate the DTO with the data of the food plate to register.
     */
    @Override
    public void saveFoodPlate(String email, FoodPlateRegisterDto foodPlate) {
        FoodPlate p = foodPlateDtoMapper.toPlatoFromSave(foodPlate);
        p.setActive(true);
        platoServicePort.saveFoodPlate(email, p);
    }
    /**
     * Updates and saves the data of a food plate in the application. Map the data of the update DTO to food plate and
     * calls the service in charge of update the food plate.
     * @param foodPlate the DTO with the data of the food plate to update.
     */
    @Override
    public void updateFoodPlate(String email, FoodPlateUpdateDto foodPlate) {
        platoServicePort.updateFoodPlate(email, foodPlateDtoMapper.toPlatoFromUpdate(foodPlate));
    }

    /**
     * Changed the state of a food plate in the application. Map the data of the change state DTO to a food plate and
     * calls the service in charge of update the food plate.
     *
     * @param changeState the DTO with the new state and the id of the food plate to change.
     */
    @Override
    public void changeStateFoodPlate(FoodPlateChangeState changeState) {
        platoServicePort.changeStateFoodPlate(foodPlateDtoMapper.toChangeState(changeState));
    }
}
