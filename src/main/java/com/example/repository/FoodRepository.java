package com.example.repository;

import com.example.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

    List<FoodEntity> findAllByEnabledIsTrueOrderByIdFoodDesc();

}
