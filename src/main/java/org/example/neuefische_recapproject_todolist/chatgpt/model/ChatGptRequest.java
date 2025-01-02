package org.example.neuefische_recapproject_todolist.chatgpt.model;

import java.util.List;

public record ChatGptRequest(String model,
                             List<ChatGptMessage> messages) {
}
