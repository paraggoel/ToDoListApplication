package com.parag.todo.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parag.todo.model.Todo;
import com.parag.todo.repository.TodoRepository;
/**
 * Created by parag goel on 04/10/2018.
 */
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Collection<Todo> getAll() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }

    @PostMapping(value = "")
    public Todo add(@RequestBody Todo todo) {
        repository.save(todo);
        return todo;
    }

    @PutMapping(value = "/{id}")
    public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {

        repository.save(todo);
        return todo;
    }

    @GetMapping("/complete")
    public Collection<Todo> getComplete() {
        return repository.findAll().stream()
                .filter(todo -> todo.getCompleted())
                .collect(Collectors.toList());
    }

    @GetMapping("/pending")
    public Collection<Todo> getPending() {

        return repository.findAll().stream()
                .filter(todo -> !todo.getCompleted())
                .collect(Collectors.toList());
    }


    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/{id}")
    public Todo findById(@PathVariable("id") Long id) {
        Optional todoOption  = repository.findById(id);
    	//Todo todo = repository.findOne(example)(id);
        return (Todo) todoOption.get();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }


}