package com.example.LearningCurve.services.chatgpt;

import com.example.LearningCurve.models.TextGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatResponse generateText(TextGenerationRequest textGenerationRequest) {
        ChatRequest request = new ChatRequest(model, textGenerationRequest.getTextGenerationPrompt());

        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        return response;
    }

}
