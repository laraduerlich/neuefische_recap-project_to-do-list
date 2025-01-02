package org.example.neuefische_recapproject_todolist.chatgpt.model;

import java.util.List;

public record ChatGptResponse(List<ChatGptChoice> choices) {
}
