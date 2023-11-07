package com.example.jjj.demos.web.controller.breed;

import com.example.jjj.demos.web.pojo.Breed;
import com.example.jjj.demos.web.pojo.Cow;
import com.example.jjj.demos.web.pojo.OutputMilk;
import com.example.jjj.demos.web.service.BreedService;
import com.example.jjj.demos.web.service.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/breed")
public class BreedController {

    @Autowired
    private BreedService breedService;

    @Autowired
    private CowService cowService;

    @GetMapping("/add/{id}")
    public String addPage(@PathVariable Integer id, Model model){
        model.addAttribute("id", id);
        return "breed/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer cid, @RequestParam String date, Model model) {
        Breed breed = new Breed();
        breed.setCid(cid);
        breed.setDate(date);
        breedService.add(breed);

        Cow cow = new Cow();
        cow = cowService.findByName(cid);
        cow.setBreed("已配种");
        cowService.updateCow(cow);

        List<Breed> list = new ArrayList<>();
        list = breedService.findList(cid);
        model.addAttribute("breeds", list);
        return "breed/list";
    }

    @GetMapping("/findList/{id}")
    public String findList(@PathVariable Integer id, Model model, HttpSession session) {
        List<OutputMilk> list = new ArrayList<>();
        list = breedService.findList(id);
        model.addAttribute("breeds", list);
        String identify = (String) session.getAttribute("identify");
        model.addAttribute("identify", identify);
        return "breed/list";
    }
}
