package com.example.LearningCurve.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @QueryMapping
    public User userById(@Argument String id) {
        return userService.getUser(id);
    }

    @MutationMapping
    public String createUser(@Argument String name) {
        try {
            User newUser = new User();
            newUser.setName(name);
            return userService.createUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
