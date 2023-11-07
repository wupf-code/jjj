package com.example.jjj.demos.web.controller.cow;

import com.example.jjj.demos.web.pojo.Cow;
import com.example.jjj.demos.web.pojo.Food;
import com.example.jjj.demos.web.pojo.House;
import com.example.jjj.demos.web.pojo.OutputMilk;
import com.example.jjj.demos.web.service.CowService;
import com.example.jjj.demos.web.service.FoodService;
import com.example.jjj.demos.web.service.HouseService;
import com.example.jjj.demos.web.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cow")
public class CowManager {
    @Autowired
    private CowService cowService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private OutputService outputService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/addCow")
    public String addCowPage() {
        return "cow/addCow";
    }

    @GetMapping("/cowList")
    public String cowList(Model model, HttpSession session){
        List<Cow> cows = new ArrayList<>();
        cows = cowService.getAll();
        model.addAttribute("cows", cows);
        String identify = (String) session.getAttribute("identify");
        model.addAttribute("identify", identify);
        return "cow/cowList";
    }

    @PostMapping("/addCow")
    public String addCow(Model model, @RequestParam String username, @RequestParam String age, @RequestParam String house, @RequestParam String date){
        Cow cow = new Cow();
        Cow findCow = cowService.findByName(Integer.valueOf(username));
        if(findCow != null){
            model.addAttribute("error", "奶牛已存在");
            return "cow/addCow";
        }
        House findHouse = houseService.findByName(house);
        if(findHouse != null && findHouse.getOccupied() >= findHouse.getCapacity()) {
            model.addAttribute("error", "当前牛舍已满");
            return "cow/addCow";
        }
        if(findHouse == null) {
            model.addAttribute("error", "牛舍不存在");
            return "cow/addCow";
        }
        findHouse.setOccupied(1+findHouse.getOccupied());
        cow.setName(Integer.valueOf(username));
        cow.setAge(age);
        cow.setHouse(house);
        cow.setDate(date);
        cow.setBreed("未配种");
        cowService.addCow(cow);
        return "cow/addCow";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        cowService.deleteById(id);
        return "redirect:/cow/cowList";
    }

    @GetMapping("/find/{id}")
    public String cowInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
//        List<OutputMilk> list;
//        list = outputService.findList(id);
//        model.addAttribute("outputMilk", list);
        return "cow/info";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model, HttpSession session){
        Cow cow;
        cow = cowService.findByName(id);
        String identify = (String) session.getAttribute("identify");
        model.addAttribute("cow", cow);
        model.addAttribute("identify", identify);
        return "cow/edit";
    }

    @PostMapping("/edit")
    public String edit(Cow cow, Model model, HttpSession session) {
        Food food = foodService.getByName(cow.getFood());
        if(food == null) {
            model.addAttribute("error", "该饲料不存在");
            model.addAttribute("cow", cow);
            String identify = (String) session.getAttribute("identify");
            model.addAttribute("identify", identify);
            return "cow/edit";
        } else if(food.getCount() < Integer.valueOf(cow.getFoodCount())) {
            model.addAttribute("error", "饲料不足");
            model.addAttribute("cow", cow);
            String identify = (String) session.getAttribute("identify");
            model.addAttribute("identify", identify);
            return "cow/edit";
        }
        food.setCount(food.getCount() - Integer.valueOf(cow.getFoodCount()));
        foodService.update(food);
        cowService.updateCow(cow);
        return "redirect:/cow/cowList";
    }

}
