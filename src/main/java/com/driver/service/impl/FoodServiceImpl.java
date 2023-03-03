package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImpl {

    @Autowired
    FoodRepository foodRepository;



    public FoodDetailsResponse getFood( String id) throws Exception{

        Long id1 = foodRepository.findByFoodId(id).getId();
        FoodEntity foodEntity = foodRepository.findById(id1).get();

        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
        foodDetailsResponse.setFoodId(foodEntity.getFoodId());
        foodDetailsResponse.setFoodName(foodEntity.getFoodName());
        foodDetailsResponse.setFoodCategory(foodEntity.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodEntity.getFoodPrice());
        return foodDetailsResponse;
    }


    public FoodDetailsResponse createFood( FoodDetailsRequestModel foodDetails) {

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodEntity.setFoodId(String.valueOf(UUID.randomUUID()));

        foodRepository.save(foodEntity);

        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
        foodDetailsResponse.setFoodId(foodEntity.getFoodId());
        foodDetailsResponse.setFoodName(foodEntity.getFoodName());
        foodDetailsResponse.setFoodCategory(foodEntity.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodEntity.getFoodPrice());
        return foodDetailsResponse;
    }


    public FoodDetailsResponse updateFood( String id, FoodDetailsRequestModel foodDetails) throws Exception{
        if(foodRepository.findByFoodId(id) == null) {
        throw new Exception("Food Not Found");
        }

            Long id1 = foodRepository.findByFoodId(id).getId();
            FoodEntity foodEntity = foodRepository.findById(id1).get();

            foodEntity.setFoodCategory(foodDetails.getFoodCategory());
            foodEntity.setFoodName(foodDetails.getFoodName());
            foodEntity.setFoodPrice(foodDetails.getFoodPrice());
            foodEntity.setFoodId(String.valueOf(UUID.randomUUID()));

        foodRepository.save(foodEntity);

        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
        foodDetailsResponse.setFoodId(foodEntity.getFoodId());
        foodDetailsResponse.setFoodName(foodEntity.getFoodName());
        foodDetailsResponse.setFoodCategory(foodEntity.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodEntity.getFoodPrice());
        return foodDetailsResponse;

    }


    public OperationStatusModel deleteFood( String id) throws Exception{

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName("Delete The Food");

        FoodEntity foodEntity = foodRepository.findByFoodId(id);

        foodRepository.delete(foodEntity);
        operationStatusModel.setOperationResult("Deleted Successfully");

        return null;
    }


    public List<FoodDetailsResponse> getFoods() {

        List<FoodDetailsResponse> foodDetailsResponseList = new ArrayList<>();
        for(FoodEntity foodEntity : foodRepository.findAll()){
            FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
            foodDetailsResponse.setFoodId(foodEntity.getFoodId());
            foodDetailsResponse.setFoodName(foodEntity.getFoodName());
            foodDetailsResponse.setFoodCategory(foodEntity.getFoodCategory());
            foodDetailsResponse.setFoodPrice(foodEntity.getFoodPrice());
            foodDetailsResponseList.add(foodDetailsResponse);
        }

        return foodDetailsResponseList;
    }
}