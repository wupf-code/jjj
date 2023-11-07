package com.example.jjj.demos.web.service;

import com.example.jjj.demos.web.pojo.User;


import java.util.List;
import java.util.Map;

public interface UserService {
    int add (String username, String password ,  String identity);
    List<User> getList();
    Map<String, String> remove (Integer id);
    Map<String, String>  update (User user);

    User queryByName(String name);
}
