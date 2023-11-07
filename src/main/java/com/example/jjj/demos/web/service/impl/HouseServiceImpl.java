package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjj.demos.web.mapper.HouseMapper;
import com.example.jjj.demos.web.pojo.House;
import com.example.jjj.demos.web.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public House findByName(String name) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return houseMapper.selectOne(queryWrapper);
    }

    @Override
    public int addHouse(House house) {
        return  houseMapper.insert(house);
    }

    @Override
    public List findList() {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        return houseMapper.selectList(null);
    }
}
