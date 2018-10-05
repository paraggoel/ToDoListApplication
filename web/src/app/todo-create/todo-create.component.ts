import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Todo } from '../todo';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-todo-create',
  templateUrl: './todo-create.component.html',
  styleUrls: ['./todo-create.component.css']
})


export class TodoCreateComponent implements OnInit {
  todo = new Todo;
  submitted = false;
  constructor(private todoService: TodoService,
    private location: Location) {}

  ngOnInit() {
  }

  newTodo(): void {
    this.submitted = false;
    this.todo = new Todo();
  }

  private save(): void {
    this.todo.completed = false;
    this.todoService.create(this.todo).then(() => this.goBack());
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goBack(): void {
    window.location.replace('');
  }
}
