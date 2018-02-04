import { Component, OnInit } from '@angular/core';
import {TodoItemModel} from "../../shared/models/todoItem/todo.item.model";
import {TodoItemService} from "../../shared/services/todoItem/todo.item.service";
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {

  todoItemList:TodoItemModel[];
  todoItemTitleFormControl = new FormControl('', [Validators.required,]);
  todoItemDescriptionFormControl = new FormControl('', [Validators.required,]);

  constructor(
    private authService:AuthService,
    private todoItemService:TodoItemService) {
    this.getAllTodoItemByUserName();
    this.getAllTodoItem();
  }

  ngOnInit() {
  }

  public onAddingTodoItem(){
    console.log(this.authService.currentUser.username);
    let todoItem = new TodoItemModel();
    todoItem.description = this.todoItemDescriptionFormControl.value;
    todoItem.name = this.todoItemTitleFormControl.value;
    todoItem.userName = this.authService.currentUser.username;
    this.todoItemService.addTodoItem(todoItem).subscribe(data => {
      this.getAllTodoItem();
    })
  }

  public onDeletingTodoItem(id){
    this.todoItemService.deleteTodoItem(id).subscribe(data => {
      this.getAllTodoItemByUserName();
    })
  }

  public getAllTodoItemByUserName()
  {
    this.todoItemService.getAllByUserName().subscribe(data=>{
      this.todoItemList = data;
    })
  }

  public getAllTodoItem()
  {
    this.todoItemService.getAll().subscribe(data=>{
      this.todoItemList = data;
    })
  }



}
