package com.example.LearningCurve.text;

import com.example.LearningCurve.user.User;
import com.example.LearningCurve.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TextController {

    @Autowired
    TextService textService;

    @Autowired
    UserService userService;

    @QueryMapping
    public Text textById(@Argument String id) {
        return textService.getText(id);
    }

    @MutationMapping
    public String createText(@Argument String text, @Argument String authorId) {
        try {
            Text newText = new Text();
            newText.setText(text);
            newText.setAuthorId(authorId);
            return textService.createText(newText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SchemaMapping
    public User author(Text text) {
        return userService.getUser(text.getAuthorId());
    }


}
