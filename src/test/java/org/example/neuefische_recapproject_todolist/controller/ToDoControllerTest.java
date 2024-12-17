package org.example.neuefische_recapproject_todolist.controller;

import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.example.neuefische_recapproject_todolist.repo.ToDoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepo repo;

    @Test
    void getAllToDos_shouldReturnEmptyList_whenCalledInitially() throws Exception {
        // WHEN & THEN
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getToDoById_shouldReturnToDo_whenCalledWithValidId() throws Exception {
        // GIVEN
        ToDo toDo = ToDo.builder()
                .id("1")
                .build();
        repo.save(toDo);
        // WHEN & THEN
        mockMvc.perform(get("/api/todo/" + toDo.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                          "id": "1"
                                          }
                                          """));
    }

    @Test
    void createToDo_shouldReturnToDo_whenCalledWithDTO() throws Exception {
        // GIVEN
        // WHEN & THEN
        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": "1",
                                  "description": "Description",
                                  "status": "OPEN"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                          "description": "Description",
                                          "status": "OPEN"
                                          }
                                          """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void updateToDo_shouldReturnToDo_whenCalledWithDTO() throws Exception {
        // GIVEN
        ToDo toDo = ToDo.builder()
                .id("1")
                .build();
        repo.save(toDo);
        // WHEN & THEN
        mockMvc.perform(put("/api/todo/" + toDo.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                 "id": 1,
                                 "description": "Description",
                                 "status": "IN_PROGRESS"
                                 }
                                 """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                          {
                                          "id": "1",
                                          "description": "Description",
                                          "status": "IN_PROGRESS"
                                          }
                                          """));
    }

    @Test
    void deleteToDo_shouldDeleteToDo_whenCalledWithValidId () throws Exception {
        // GIVEN
        ToDo toDo = ToDo.builder()
                .id("1")
                .build();
        repo.save(toDo);
        // WHEN & THEN
        assertTrue(repo.existsById(toDo.id()));
        mockMvc.perform(delete("/api/todo/" + toDo.id()))
                .andExpect(status().isOk());
        assertFalse(repo.existsById(toDo.id()));
    }
}