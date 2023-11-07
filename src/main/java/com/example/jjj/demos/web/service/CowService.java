package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.Cow;

import java.util.List;


public interface CowService {

    public int addCow(Cow cow);

    public Cow findByName(Integer name);
    public List<Cow> getAll();

    public int deleteById(Integer id);

    public int updateCow(Cow cow);
}
