package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.OutputMilk;

import java.util.List;

public interface OutputService {

    public int add(OutputMilk outputMilk);

    public List findList(Integer cid);

}
