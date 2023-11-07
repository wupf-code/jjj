/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jjj.demos.web.controller;

import com.example.jjj.demos.web.pojo.User;
import com.example.jjj.demos.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @Autowired
    UserService userService;

    // http://127.0.0.1:8080/
    @GetMapping("/")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(HttpSession session, HttpServletResponse response, HttpServletRequest request, @RequestParam String username, @RequestParam String password, Model model ) throws IOException {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String inputCode = request.getParameter("captcha");
        if(inputCode==null){
            model.addAttribute("error", "验证码为空"); // 添加错误消息
            return "login";
        }
        if(!inputCode.equals(verifyCodeExpected)){
            model.addAttribute("error","验证码错误");
            return "login";
        }else{
            if(verifyLogin(username, password)){
                User user = userService.queryByName(username);
                //model.addAttribute("user", user);
                session.setAttribute("user", user);
                session.setAttribute("identify", user.getIdentify());
                return "redirect:/html";
            }
            model.addAttribute("error", "用户名或密码错误");
            return  "login";
        }

    }

    // 注册页面GET请求处理方法
    @GetMapping("/register")
    public String registerPage() {
        return "regist";
    }

    // 注册表单POST请求处理方法
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password , @RequestParam String identity, Model model) {
        // 处理注册逻辑，将用户信息保存到数据库
        if(verifyRegister(username)) {
            userService.add(username, password, identity);
        } else {
            model.addAttribute("error", "用户已存在");
            return "regist";
        }
        // 注册成功后，重定向到登录页面
        return "/login";
    }

    public boolean verifyRegister(String username) {
        User user = userService.queryByName(username);
        return null == user;
    }
    public boolean verifyLogin(String username, String password){
        User user = userService.queryByName(username);
        if(null == user){
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }


    // http://127.0.0.1:8080/html
    @GetMapping("/html")
    public String html(Model model, HttpSession session) {
        User user = new User();
        user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/welcome")
    public String sysWelcome(){
        return "controlConsole";
    }


    @GetMapping("/signOut")
    public String singOut(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("identify");
        return "login";
    }

}
