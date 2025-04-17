package org.example.neuefische_recapproject_todolist.chatgpt.service;

import org.example.neuefische_recapproject_todolist.chatgpt.model.ChatGptMessage;
import org.example.neuefische_recapproject_todolist.chatgpt.model.ChatGptRequest;
import org.example.neuefische_recapproject_todolist.chatgpt.model.ChatGptResponse;
import org.example.neuefische_recapproject_todolist.exception.ChatGPTNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGptApiService {

    private final RestClient restClient;

    @Value("${app.chatgpt.api.key}")
    private String apiKey;


    public ChatGptApiService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public String getChatGptResponse(String description) throws ChatGPTNull {
        ChatGptResponse response = restClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ChatGptRequest(
                        "gpt-3.5-turbo",
                        List.of(new ChatGptMessage("user", "Correct the spelling only:" + description))
                ))
                .retrieve()
                .body(ChatGptResponse.class);
        if (response == null) {
            throw new ChatGPTNull("Response is null");
        } else {
            return response.choices().getFirst().message().content();
        }
    }
}
