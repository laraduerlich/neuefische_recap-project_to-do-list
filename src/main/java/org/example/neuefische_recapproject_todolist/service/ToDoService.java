package org.example.neuefische_recapproject_todolist.service;

import lombok.RequiredArgsConstructor;
import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.example.neuefische_recapproject_todolist.model.ToDoDTO;
import org.example.neuefische_recapproject_todolist.model.ToDoStatus;
import org.example.neuefische_recapproject_todolist.repo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo repo;
    private final IdService idService;

    public List<ToDoDTO> getAllToDos() {
        return repo.findAll().stream()
                .map(toDo -> {
                    ToDoDTO dto = new ToDoDTO(
                            toDo.description(),
                            toDo.status());
                    return dto;
                })
                .toList();
    }

    public ToDo getToDoById(String id){
        return repo.findById(id).orElseThrow();
    }

    public ToDo createToDo(ToDoDTO dto) {
        ToDo newToDo = new ToDo(
                idService.generateId(),
                dto.description(),
                ToDoStatus.OPEN
                );
        return repo.save(newToDo);
    }

    public ToDo updateToDo (ToDoDTO dto, String id){
        if (repo.existsById(id)) {
            ToDo temp = repo.findById(id).orElseThrow();
            temp = temp.withStatus(dto.status());
            temp = temp.withDescription(dto.description());
            return repo.save(temp);
        } else {
            throw new RuntimeException("ToDo not found");
        }
    }

    public void deleteToDo (String id){
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("ToDo not found");
        }
    }



}
