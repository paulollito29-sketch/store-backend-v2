package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.FoodMapper;
import com.example.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService( FoodRepository foodRepository){
        this.foodRepository=foodRepository;
    }

    public List<FoodFindAll> FindALl(){
        return foodRepository.findAllByEnabledIsTrueOrderByIdFoodDesc().stream().map(FoodMapper::toFoodFindAll).toList();
    }

    public FoodCreated create(FoodCreate dto){
        if (foodRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())){
            throw new ResourceAlreadyExistsException("this food already exists");
        }
        var foodEntity = FoodMapper.toEntityCreated(dto);
        var foodSaved = foodRepository.save(foodEntity);
        return  FoodMapper.toFoodCreated(foodSaved);
    }

    public FoodFindOne findOne(long id){
        var food = foodRepository.findFirstByEnabledIsTrueAndIdFood(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food doesnt exists"));
        return FoodMapper.toFoodFindOne(food);
    }

    public FoodUpdated update(Long id, FoodUpdate dto){
        var food = foodRepository.findFirstByEnabledIsTrueAndIdFood(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food doesnt exists"));
        if (foodRepository.existsByEnabledIsTrueAndIdFoodNotAndNameIgnoreCase(id, dto.name())){
            throw new ResourceAlreadyExistsException("this food already exists");
        }
        var foodEntity = FoodMapper.toEntityUpdated(food, dto);
        var foodSaved = foodRepository.save(foodEntity);
        return  FoodMapper.toFoodUpdated(foodSaved);
    }

    public void delete(Long id){
        var food = foodRepository.findFirstByEnabledIsTrueAndIdFood(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food doesnt exists"));
        var foodDeleted = FoodMapper.toEntityDeleted(food);
        foodRepository.save(foodDeleted);
    }
}
