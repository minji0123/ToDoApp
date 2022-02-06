package com.example.TodoList.entity;

import com.example.TodoList.dto.TodoDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    @Column(columnDefinition = "boolean default false")
    private Boolean completed;

    // 수정할 때 title 이랑 content 값을 안넣어주면은 null 이니까
    // 그거는 반영하면 안됨
    public void patch(Todo todo){
        if (todo.title != null)
            this.title = todo.title;

        if (todo.content != null){
            this.content = todo.content;
        }
        if (todo.completed != null){
            this.completed = todo.completed;
        }
    }


}
// patch 는 dto 가 들어왔을 때 dto 에 있는 값을 어떻게 entity 에 반영할지 결정하는 함수이다.