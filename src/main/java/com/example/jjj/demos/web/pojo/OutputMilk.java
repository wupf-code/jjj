package com.example.jjj.demos.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputMilk {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer cid;
    String date;
    String output;
    String quality;
}
