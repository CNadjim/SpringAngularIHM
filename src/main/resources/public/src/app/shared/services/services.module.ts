import { NgModule } from '@angular/core';
import {UserService} from './user/user.service';
import {AuthService} from "./auth/auth.service";

@NgModule({
  providers: [
    UserService,
    AuthService
  ]
})
export class ServicesModule { }
