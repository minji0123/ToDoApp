package com.example.TodoList.controller;

import com.example.TodoList.dto.TodoDto;
import com.example.TodoList.entity.Todo;
import com.example.TodoList.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // 새 글 생성 페이지
    @GetMapping("/todos/new")
    public String newTodoForm (){
        return "todos/new";
    }

    // 새 글 post
    @PostMapping("/todos/create")
    public String createNewTodo (TodoDto todoDto){
        log.info(todoDto.toString());

        Todo todoEntity = todoDto.toEntity();
        log.info(todoEntity.toString());

        Todo saved = todoRepository.save(todoEntity);
        log.info(saved.toString());

        return "redirect:/todos/" + saved.getId();
    }

    // 전체목록 페이지
    @GetMapping("/todos")
    public String index (Model model){
        List<Todo> todoEntityList = todoRepository.findAll();
        model.addAttribute("todoList",todoEntityList);
        return "todos/index";
    }

    // 세부정보 페이지
    @GetMapping("/todos/{id}")
    public String show (@PathVariable Long id, Model model){
        Todo todoEntity = todoRepository.findById(id).orElse(null);
        model.addAttribute("todo", todoEntity);
        return "todos/show";
    }

    // 글 수정
    @GetMapping("/todos/{id}/edit")
    public String edit (@PathVariable Long id, Model model){
        Todo todoEntity = todoRepository.findById(id).orElse(null);
        model.addAttribute("todo", todoEntity);
        return "todos/edit";
    }

    // 글 수정 patch
    @PostMapping("/todos/update")
    public String editUpdate (TodoDto todoDto){
        log.info(todoDto.toString());

        Todo todoEntity = todoDto.toEntity();
        log.info(todoEntity.toString());

        Todo target = todoRepository.findById(todoEntity.getId()).orElse(null);

        if (target != null){
            Todo reSaved = todoRepository.save(todoEntity);
            log.info(reSaved.toString());
        }

        return "redirect:/todos/" + todoEntity.getId();
    }

    // 글 삭제
    @GetMapping("/todos/{id}/delete")
    public String delete (@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제ㄱ");

        Todo todoEntity = todoRepository.findById(id).orElse(null);
        log.info(todoEntity.toString());

        if (todoEntity != null){
            todoRepository.delete(todoEntity);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }
        return "redirect:/todos";
    }

}
