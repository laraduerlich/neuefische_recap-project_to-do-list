package org.example.neuefische_recapproject_todolist.service;

import org.example.neuefische_recapproject_todolist.chatgpt.service.ChatGptApiService;
import org.example.neuefische_recapproject_todolist.exception.ChatGPTNull;
import org.example.neuefische_recapproject_todolist.exception.NotFoundException;
import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.example.neuefische_recapproject_todolist.model.ToDoDTO;
import org.example.neuefische_recapproject_todolist.repo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepo repo;
    private final IdService idService;
    private final ChatGptApiService chatGptApiService;

    public ToDoService(ToDoRepo repo, IdService idService, ChatGptApiService chatGptApiService) {
        this.repo = repo;
        this.idService = idService;
        this.chatGptApiService = chatGptApiService;
    }

    public List<ToDo> getAllToDos() {
        return repo.findAll();
    }

    public ToDo getToDoById(String id) throws NotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("ToDo not found"));
    }

    public ToDo createToDo(ToDoDTO dto) throws ChatGPTNull {
        String spellingCheck = chatGptApiService.getChatGptResponse(dto.description());
        ToDo newToDo = new ToDo(
                idService.generateId(),
                spellingCheck,
                dto.status());
        return repo.save(newToDo);
    }

    public ToDo updateToDo (ToDo toDo) throws NotFoundException {
        if (repo.existsById(toDo.id())) {
            String spellingCheck = chatGptApiService.getChatGptResponse(toDo.description());
            toDo = toDo.withDescription(spellingCheck);
            return repo.save(toDo);
        } else {
            throw new NotFoundException("ToDo not found");
        }
    }

    public void deleteToDo (String id) throws NotFoundException {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new NotFoundException("ToDo not found");
        }
    }
}
