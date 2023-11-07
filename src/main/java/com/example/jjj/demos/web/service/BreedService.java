package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.Breed;


import java.util.List;

public interface BreedService {
    public int add(Breed breed);

    public List findList(Integer cid);
}
