package com.example.TodoList.entity;

import lombok.*;

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


    public void patch(Todo todo){
        if (todo.title != null)
            this.title = todo.title;

        if (todo.content != null){
            this.content = todo.content;
        }
    }
}
