package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjj.demos.web.mapper.OutputMapper;
import com.example.jjj.demos.web.pojo.OutputMilk;
import com.example.jjj.demos.web.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    private OutputMapper outputMapper;
    @Override
    public int add(OutputMilk outputMilk) {
        return outputMapper.insert(outputMilk);
    }

    @Override
    public List findList(Integer cid) {
        QueryWrapper<OutputMilk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        return outputMapper.selectList(queryWrapper);
    }
}
