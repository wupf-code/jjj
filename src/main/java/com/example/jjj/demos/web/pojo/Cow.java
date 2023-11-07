package com.example.jjj.demos.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cow {
    Integer name;
    String age;
    String house;
    String date;
    String food;
    String foodCount;
    String level;//奶牛评价
    String breed;// 配种  //为配种，可配种，已配种
}
