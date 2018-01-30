import { NgModule } from '@angular/core';
import {AdminGuard} from "./admin/admin.guard";
import {GuestGuard} from "./guest/guest.guard";
import {LoginGuard} from "./login/login.guard";
import {UserGuard} from "./user/user.guard";

@NgModule({
  providers: [
    AdminGuard,
    GuestGuard,
    LoginGuard,
    UserGuard
  ]
})
export class GuardsModule { }
