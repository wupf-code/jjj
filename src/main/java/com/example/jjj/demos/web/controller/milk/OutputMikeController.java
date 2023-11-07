package com.example.jjj.demos.web.controller.milk;

import com.example.jjj.demos.web.pojo.OutputMilk;
import com.example.jjj.demos.web.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/milk")
public class OutputMikeController {
    @Autowired
    private OutputService outputService;

    @GetMapping("/add/{id}")
    public String addPage(@PathVariable Integer id, Model model){
        model.addAttribute("id", id);
        return "milk/add";
    }

    @PostMapping("/add")
    public String addRecord(@RequestParam Integer cid, @RequestParam String date, @RequestParam String output, @RequestParam String quality, Model model) {
        OutputMilk outputMilk = new OutputMilk();
        outputMilk.setDate(date);
        outputMilk.setOutput(output);
        outputMilk.setQuality(quality);
        outputMilk.setCid(cid);
        outputService.add(outputMilk);

        List<OutputMilk> list = new ArrayList<>();
        list = outputService.findList(cid);
        model.addAttribute("milks", list);
        return "milk/list";
    }

    @GetMapping("/findList/{id}")
    public String findList(@PathVariable Integer id, Model model, HttpSession session) {
        List<OutputMilk> list = new ArrayList<>();
        list = outputService.findList(id);
        String identify = (String) session.getAttribute("identify");
        model.addAttribute("identify", identify);
        model.addAttribute("milks", list);
        return "milk/list";
    }
}
