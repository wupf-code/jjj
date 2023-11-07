package com.example.jjj.demos.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jjj.demos.web.pojo.OutputMilk;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutputMapper extends BaseMapper<OutputMilk> {
}
