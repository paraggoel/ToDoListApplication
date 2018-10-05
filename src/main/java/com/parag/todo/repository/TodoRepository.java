package com.parag.todo.repository;

import com.parag.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by parag goel on 04/10/2018.
 */
@RepositoryRestResource
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
