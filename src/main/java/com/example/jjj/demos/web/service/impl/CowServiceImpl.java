package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.jjj.demos.web.mapper.CowMapper;
import com.example.jjj.demos.web.pojo.Cow;
import com.example.jjj.demos.web.service.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CowServiceImpl implements CowService {
    @Autowired
    private CowMapper cowMapper;
    @Override
    public int addCow(Cow cow) {
        return cowMapper.insert(cow);
    }

    @Override
    public Cow findByName(Integer name) {
        QueryWrapper<Cow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return cowMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Cow> getAll() {
        return cowMapper.selectList(null);
    }

    @Override
    public int deleteById(Integer id) {
        Cow cow = findByName(id);
        QueryWrapper<Cow> wrapper = new QueryWrapper<>();
        wrapper.eq("name", id);
        cowMapper.delete(wrapper);
        return 0;
    }

    @Override
    public int updateCow(Cow cow) {

        UpdateWrapper<Cow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", cow.getName());
        cowMapper.update(cow, updateWrapper);
        return 0;
    }
}
