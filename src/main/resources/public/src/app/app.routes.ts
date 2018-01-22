import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {HomeComponent} from './routes/home/home.component';
import {UserComponent} from './routes/user/user.component';

export const ROUTES: Routes = [
  { path: '',      component: HomeComponent },
  { path: 'home',  component: HomeComponent },
  { path: 'user',  component: UserComponent },
  { path: '**',    component: HomeComponent }
];
