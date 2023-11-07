package com.example.jjj.demos.web.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jjj.demos.web.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}

