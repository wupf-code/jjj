package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.Food;

import java.util.List;

public interface FoodService {
    int add(Food food);
    int update(Food food);
    List<Food> findList();

    Food getByName(String name);

    Food getById(Integer id);
}
