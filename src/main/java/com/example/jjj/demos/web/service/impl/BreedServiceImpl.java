package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjj.demos.web.mapper.BreedMapper;
import com.example.jjj.demos.web.pojo.Breed;

import com.example.jjj.demos.web.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedMapper breedMapper;

    @Override
    public int add(Breed breed) {
        breedMapper.insert(breed);
        return 0;
    }

    @Override
    public List findList(Integer cid) {
        QueryWrapper<Breed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        return breedMapper.selectList(queryWrapper);
    }
}
