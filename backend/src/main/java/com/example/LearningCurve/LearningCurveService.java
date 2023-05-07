package com.example.LearningCurve;

import com.example.LearningCurve.models.TextGenerationRequest;
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

    @PostMapping("/submit-text-generation-request")
    public String submitTextGenerationRequest(@RequestBody TextGenerationRequest textGenerationRequest) {
        // TODO (jason): Generate from an LLM
        // TODO (jason): Create a text, and return its id
        return textGenerationRequest.getPrompt();
    }
}
