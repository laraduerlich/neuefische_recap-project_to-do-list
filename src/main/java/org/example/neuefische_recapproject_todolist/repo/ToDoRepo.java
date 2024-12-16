package org.example.neuefische_recapproject_todolist.repo;

import org.example.neuefische_recapproject_todolist.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends MongoRepository<ToDo, String> {

}
