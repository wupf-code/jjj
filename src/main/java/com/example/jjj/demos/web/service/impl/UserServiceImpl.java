package com.example.jjj.demos.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jjj.demos.web.mapper.UserMapper;
import com.example.jjj.demos.web.pojo.User;
import com.example.jjj.demos.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(String username,  String password , String identity) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setIdentify(identity);
        return userMapper.insert(user);
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public Map<String, String> remove(Integer id) {
        return null;
    }

    @Override
    public Map<String, String> update(User user) {
        return null;
    }

    @Override
    public User queryByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return userMapper.selectOne(wrapper);
    }
}
