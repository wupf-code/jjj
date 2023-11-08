package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjj.demos.web.mapper.FoodMapper;
import com.example.jjj.demos.web.pojo.Food;
import com.example.jjj.demos.web.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public int add(Food food) {
        return foodMapper.insert(food);
    }

    @Override
    public int update(Food food) {
        return foodMapper.updateById(food);
    }

    @Override
    public List<Food> findList() {
        List list = new ArrayList();

        list = foodMapper.selectList(null);
        return list;
    }

    @Override
    public Food getByName(String name) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return foodMapper.selectOne(queryWrapper);
    }

    @Override
    public Food getById(Integer id) {
        return foodMapper.selectById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return foodMapper.deleteById(id);
    }
}
