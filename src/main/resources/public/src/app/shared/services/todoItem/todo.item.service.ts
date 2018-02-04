import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {LoginRequestModel} from "../../models/auth/login.request.model";
import {UserModel} from "../../models/user/user.model";
import {UserTokenModel} from "../../models/auth/user.token.model";
import {AuthService} from "../auth/auth.service";
import {TodoItemModel} from "../../models/todoItem/todo.item.model";

@Injectable()
export class TodoItemService {

  constructor(
    private authService: AuthService,
    private http: HttpClient) {
  }

  public getAllByUserName(): Observable<any> {
    return this.http.get('/api/todoitem/'+this.authService.currentUser.username);
  }

  public deleteTodoItem(id){
    return this.http.delete('/api/todoitem/'+id);
  }

  public addTodoItem(todoItem:TodoItemModel){
    return this.http.post('/api/todoitem/add',todoItem);
  }

  public getAll(): Observable<any> {
    return this.http.get('/api/todoitem');
  }


}
