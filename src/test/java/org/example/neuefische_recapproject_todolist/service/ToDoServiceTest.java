package org.example.neuefische_recapproject_todolist.service;

import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.example.neuefische_recapproject_todolist.model.ToDoDTO;
import org.example.neuefische_recapproject_todolist.model.ToDoStatus;
import org.example.neuefische_recapproject_todolist.repo.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    private ToDoRepo repo = mock(ToDoRepo.class);
    private IdService idService = mock(IdService.class);

    @Test
    void getAllToDos_shouldReturnEmptyList_whenCalledInitially(){
        // GIVEN
        ToDoService service = new ToDoService(repo,idService);
        List<ToDoDTO> expected = Collections.emptyList();
        // WHEN
        List<ToDoDTO> actual = service.getAllToDos();
        // THEN
        assertEquals(expected,actual);
    }

    @Test
    void getToDoById_shouldReturnToDo_whenCalledWithValidId() {
        // GIVEN
        ToDoService service = new ToDoService(repo,idService);
        ToDo toDo = new ToDo("1", "Test", ToDoStatus.OPEN);
        ToDo expected = new ToDo("1", "Test", ToDoStatus.OPEN);
        when(repo.findById(toDo.id())).thenReturn(Optional.of(toDo));
        // WHEN
        ToDo actual = service.getToDoById(toDo.id());
        // THEN
        assertEquals(expected,actual);
    }

    @Test
    void createToDo_shouldCreateToDo_whenCalledWithDTO(){
        // GIVEN
        ToDoService service = new ToDoService(repo,idService);
        ToDoDTO dto = new ToDoDTO("Test", ToDoStatus.OPEN);
        ToDo expected = new ToDo("1", "Test", ToDoStatus.OPEN);
        when(idService.generateId()).thenReturn("1");
        when(repo.save(expected)).thenReturn(expected);
        // WHEN
        ToDo actual = service.createToDo(dto);
        // THEN
        assertEquals(expected,actual);
        verify(repo).save(expected);
    }

    @Test
    void updateToDo_shouldUpdateToDo_whenCalledwithDTO() {
        // GIVEN
        ToDoService service = new ToDoService(repo,idService);
        ToDoDTO dto = new ToDoDTO("Test", ToDoStatus.OPEN);
        ToDo toDo = new ToDo("1", "Test", ToDoStatus.OPEN);
        ToDo expected = new ToDo(
                toDo.id(),
                dto.description(),
                dto.status());
        when(repo.existsById(toDo.id())).thenReturn(true);
        when(repo.findById(toDo.id())).thenReturn(Optional.of(toDo));
        when(repo.save(any(ToDo.class))).thenReturn(expected);
        // WHEN
        ToDo actual = service.updateToDo(dto, toDo.id());
        // THEN
        assertEquals(expected,actual);
        verify(repo).save(expected);
    }

    @Test
    void deleteToDo_shouldDeleteToDo_whenCalledWithValidId() {
        // GIVEN
        ToDoService service = new ToDoService(repo,idService);
        ToDo toDo = new ToDo("1", "Test", ToDoStatus.OPEN);
        ToDo expected = new ToDo(
                toDo.id(),
                toDo.description(),
                toDo.status());
        when(repo.existsById(toDo.id())).thenReturn(true);
        // WHEN
        service.deleteToDo(toDo.id());
        // THEN
        verify(repo).deleteById(expected.id());
    }


  
}