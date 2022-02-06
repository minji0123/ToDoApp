package com.example.TodoList.controller;

import com.example.TodoList.dto.TodoDto;
import com.example.TodoList.entity.Todo;
import com.example.TodoList.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TodoApiController {

    @Autowired
    private TodoRepository todoRepository;

    // 글 수정 patch
    @PatchMapping("/todos/update/{id}")
    public ResponseEntity<TodoDto> editUpdate (@PathVariable Long id, @RequestBody TodoDto todoDto){

        Todo target = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 todo 가 없어요!"));

        target.patch(todoDto.toEntity());

        Todo updated = todoRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
