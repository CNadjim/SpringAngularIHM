import { Component, OnInit } from '@angular/core';
import {TodoItemService} from "../../shared/services/todoItem/todo.item.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private todoItemService: TodoItemService) { }


  public getItem(){
    this.todoItemService.getAll().subscribe(data =>{
console.log(data);
    },

      error => {

      })
  }

  ngOnInit() {
  }

}
