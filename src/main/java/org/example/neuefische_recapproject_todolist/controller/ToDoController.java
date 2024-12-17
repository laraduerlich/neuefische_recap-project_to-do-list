package org.example.neuefische_recapproject_todolist.controller;

import lombok.RequiredArgsConstructor;
import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.example.neuefische_recapproject_todolist.model.ToDoDTO;
import org.example.neuefische_recapproject_todolist.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @GetMapping
    public List<ToDo> getAllToDos (){
        return service.getAllToDos();
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id){
        return service.getToDoById(id);
    }

    @PostMapping
    public ToDo createToDo (@RequestBody ToDoDTO dto){
        return service.createToDo(dto);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@RequestBody ToDo toDo){
        return service.updateToDo(toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id){
        service.deleteToDo(id);
    }

}
