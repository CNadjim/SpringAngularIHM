import { NgModule } from '@angular/core';
import {AuthService} from "./auth/auth.service";
import {TodoItemService} from "./todoItem/todo.item.service";
import {UserService} from "./user/user.service";

@NgModule({
  providers: [
    AuthService,
    UserService,
    TodoItemService
  ]
})
export class ServicesModule { }
