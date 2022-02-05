package com.example.TodoList.dto;

import com.example.TodoList.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TodoDto {
    private Long id;
    private String title;
    private String content;


    public Todo toEntity(){
        return new Todo(id,title,content);
    }
}
