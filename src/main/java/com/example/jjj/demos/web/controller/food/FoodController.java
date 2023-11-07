package com.example.jjj.demos.web.controller.food;

import com.example.jjj.demos.web.pojo.Food;
import com.example.jjj.demos.web.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/addFood")
    public String addPage() {
        return "food/add";
    }
    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam Integer count, Model model){
        Food oldFood = new Food();
        oldFood = foodService.getByName(name);
        if(oldFood != null) {
            model.addAttribute("error", "配方已存在");
            return "food/add";
        }
        if(count <=0) {
            model.addAttribute("error", "数量不能为0");
            return "food/add";
        }
        Food food = new Food();
        food.setName(name);
        food.setCount(count);
        foodService.add(food);
        return "food/add";
    }

    @GetMapping("/findList")
    public String list(Model model) {
        List<Food> list = new ArrayList<>();
        list = foodService.findList();
        model.addAttribute("foods", list);
        return "food/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable Integer id) {
        Food food = foodService.getById(id);
        model.addAttribute("food", food);
        return "food/edit";
    }
    @PostMapping("/edit")
    public String edit(Food food) {
        foodService.update(food);
        return "redirect:/food/findList";
    }
}
