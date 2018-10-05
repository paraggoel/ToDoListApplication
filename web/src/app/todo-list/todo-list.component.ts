import { Component, Input, OnInit } from '@angular/core';
import { Todo } from '../todo';
import { TodoService } from '../todo.service';
import { TodoCreateComponent } from '../todo-create/todo-create.component';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css'],
  providers: [TodoService]
})

export class TodoListComponent implements OnInit {
  todos: Todo[];
  completeds: Todo[];
  pendings: Todo[];

  selectedTodo: Todo;

  @Input() todo: Todo;


  constructor(private todoService: TodoService) {}

  getTodos() {
     this.todoService.getTodos().then(todos => this.todos = todos);
  }

  getCompleteds() {
     this.todoService.getCompleteds().then(completeds => this.completeds = completeds);
  }

  getPendings() {
     this.todoService.getPendings().then(pendings => this.pendings = pendings);
  }

  update(todo: Todo): void {
    console.log(todo);
    this.todoService.update(todo).then(() => this.goBack());
  }

  delete(todo: Todo): void {
    this.todoService.delete(todo.id).then(() => this.goBack());
  }


  ngOnInit(): void {
     this.getTodos();
     this.getCompleteds();
     this.getPendings();
  }

  onSelect(todo: Todo): void {
    console.log(todo);

    this.selectedTodo = todo;
  }

  goBack(): void {
    window.location.replace('');
  }
}
