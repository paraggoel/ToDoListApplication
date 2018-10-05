import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { Todo } from './todo';


@Injectable()
export class TodoService {

  constructor(private http: Http) {}

  private todosUrl = 'todo';  // URL to web API
  private headers = new Headers({'Content-Type': 'application/json'});

  // Get all todos
  getTodos(): Promise<Todo[]> {
    return this.http.get(this.todosUrl)
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }


  // Get all Completeds
  getCompleteds(): Promise<Todo[]> {
    return this.http.get(this.todosUrl + "/complete")
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }


//  // Get all Pending
  getPendings(): Promise<Todo[]> {
    return this.http.get(this.todosUrl + "/pending")
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }


  getTodoById(id: number): Promise<Todo[]> {
    const url = `${this.todosUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Todo)
      .catch(this.handleError);
  }

  create(todo: Todo): Promise<Todo> {
      const url = `${this.todosUrl}`;
    return this.http
      .post(url, JSON.stringify(todo), {headers : this.headers})
      .toPromise()
      .then(res => res.json() as Todo)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.todosUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(todo: Todo): Promise<void> {
      console.log(todo);

      const url = `${this.todosUrl}/${todo.id}`;
      return this.http
        .put(url, JSON.stringify(todo), {headers : this.headers})
        .toPromise()
            .then(() => todo)
        .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
