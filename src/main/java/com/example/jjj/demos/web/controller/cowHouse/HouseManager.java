package com.example.jjj.demos.web.controller.cowHouse;

import com.example.jjj.demos.web.pojo.House;
import com.example.jjj.demos.web.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseManager {
    @Autowired
    private HouseService houseService;

    @GetMapping("/addCowHouse")
    public String addCowHousePage() {
        return "cow_house/add_cow_house";
    }

    @PostMapping("/addCowHouse")
    public String addCowHouse(Model model, @RequestParam String username, @RequestParam String capacity) {
        House house = new House();
        House findHouse = houseService.findByName(username);
        if(findHouse != null) {
            model.addAttribute("error", "牛舍已经存在");
            return "cow_house/add_cow_house";
        }
        house.setName(username);
        house.setCapacity(Integer.valueOf(capacity));
        house.setOccupied(0);
        houseService.addHouse(house);
        return "cow_house/add_cow_house";
    }

    @GetMapping("/findList")
    public String findList(Model model){
        List<House> list = new ArrayList<>();
        list = houseService.findList();
        model.addAttribute("houses", list);
        return "cow_house/list";
    }
}
