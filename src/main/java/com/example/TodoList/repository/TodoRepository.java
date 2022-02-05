package com.example.TodoList.repository;

import com.example.TodoList.entity.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    ArrayList<Todo> findAll();

}
