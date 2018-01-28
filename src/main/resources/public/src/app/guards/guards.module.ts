import { NgModule } from '@angular/core';
import {AdminGuard} from "./admin/admin.guard";
import {GuestGuard} from "./guest/guest.guard";
import {LoginGuard} from "./login/login.guard";

@NgModule({
  providers: [
    AdminGuard,
    GuestGuard,
    LoginGuard
  ]
})
export class GuardsModule { }
