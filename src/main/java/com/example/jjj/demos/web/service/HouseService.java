package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.House;

import java.util.List;

public interface HouseService {
    public House findByName(String name);
    public int addHouse(House house);

    public List findList();

}
