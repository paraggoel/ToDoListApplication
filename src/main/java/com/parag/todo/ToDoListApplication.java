package com.parag.todo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parag.todo.model.Todo;
import com.parag.todo.repository.TodoRepository;

/**
 * 
 * @author parag goel
 *
 */
@SpringBootApplication
public class ToDoListApplication {

	@Autowired
	private TodoRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	public void run(String... strings) throws Exception {

		List<Todo> todos = Arrays.asList(
				new Todo("Task 1", false),
				new Todo("Task 2", false),
				new Todo("Task 3", false),
				new Todo("Task 4", false),
				new Todo("Task 5", false),
				new Todo("Task 6", false),
				new Todo("Task 7", true)
		);

		todos.forEach(todo -> 
				repository.save(todo)
		);

//		repository.findAll().forEach(System.out::println);
	}
}
