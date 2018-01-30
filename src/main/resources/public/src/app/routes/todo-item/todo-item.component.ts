import { Component, OnInit } from '@angular/core';
import {TodoItemModel} from "../../shared/models/todoItem/todo.item.model";
import {TodoItemService} from "../../shared/services/todoItem/todo.item.service";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {

  todoItemList:TodoItemModel[];

  constructor(
    private todoItemService:TodoItemService
  ) {
    this.getAllTodoItem();
  }

  ngOnInit() {
  }

  public onDeletingTodoItem(id){
    this.todoItemService.deleteTodoItem(id).subscribe(data => {
      this.getAllTodoItem();
    })
  }

  public getAllTodoItem()
  {
    this.todoItemService.getAll().subscribe(data=>{
      this.todoItemList = data;
    })
  }



}
