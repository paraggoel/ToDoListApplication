package com.parag.todo.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.parag.todo.ToDoListApplicationTests;
import com.parag.todo.model.Todo;
import com.parag.todo.repository.TodoRepository;


/**
 * Created by parag.rahman on 11/10/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToDoListApplicationTests.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
public class TodoControllerTest {

    @Autowired
    private TodoRepository repository;

    @Before
    public void setUp() throws Exception {

        List<Todo> todos = Arrays.asList(
                new Todo("Task 1", false),
                new Todo("Task 2", false),
                new Todo("Task 3", false),
                new Todo("Task 4", false),
                new Todo("Task 5", false),
                new Todo("Task 6", false),
                new Todo("Task 7", true)
        );
        repository.deleteAll();
        todos.forEach(todo -> repository.save(todo));
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void testGetAll() {
        TodoController todoController = new TodoController(repository);

        Collection<Todo> actual = todoController.getAll();
        actual.forEach(System.out::println);
        assertEquals(7, actual.size());
    }

    @Test
    public void testGetComplete() {
        TodoController todoController = new TodoController(repository);

        Collection<Todo> actual = todoController.getComplete();
        actual.forEach(System.out::println);
        assertEquals(1, actual.size());
    }

    @Test
    public void testGetPending() {
        TodoController todoController = new TodoController(repository);

        Collection<Todo> actual = todoController.getPending();
        actual.forEach(System.out::println);
        assertEquals(6, actual.size());
    }


}