import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {HomeComponent} from './routes/home/home.component';
import {UserComponent} from './routes/user/user.component';
import {LoginComponent} from "./routes/login/login.component";
import {AdminGuard} from "./guards/admin/admin.guard";
import {GuestGuard} from "./guards/guest/guest.guard";
import {TodoItemComponent} from "./routes/todo-item/todo-item.component";
import {UserGuard} from "./guards/user/user.guard";

export const ROUTES: Routes = [
  { path: '',      component: HomeComponent },
  { path: 'home',  component: HomeComponent },
  { path: 'user',  component: UserComponent, canActivate: [AdminGuard] },
  { path: 'login',  component: LoginComponent , canActivate: [GuestGuard]},
  { path: 'todo',  component: TodoItemComponent, canActivate: [UserGuard] },
  { path: '**',    component: HomeComponent }
];
