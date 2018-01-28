import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {HomeComponent} from './routes/home/home.component';
import {UserComponent} from './routes/user/user.component';
import {LoginComponent} from "./routes/login/login.component";
import {AdminGuard} from "./guards/admin/admin.guard";
import {GuestGuard} from "./guards/guest/guest.guard";

export const ROUTES: Routes = [
  { path: '',      component: HomeComponent },
  { path: 'home',  component: HomeComponent },
  { path: 'user',  component: UserComponent, canActivate: [AdminGuard] },
  { path: 'login',  component: LoginComponent , canActivate: [GuestGuard]},
  { path: '**',    component: HomeComponent }
];
