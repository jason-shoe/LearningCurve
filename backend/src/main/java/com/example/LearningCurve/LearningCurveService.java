package com.example.LearningCurve;

import com.example.LearningCurve.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LearningCurveService {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user-ids")
    @ResponseBody
    public List<String> getAllUserIds() {
        return userService.getAllUserIds();
    }
}
