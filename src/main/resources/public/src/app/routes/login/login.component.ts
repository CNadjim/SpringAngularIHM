import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {UserService} from "../../shared/services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [AuthService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usernameBufferForm : string;
  passwordBufferForm : string;

  constructor(
    private router: Router,
    private authService : AuthService,
    private userService : UserService
  ) { }

  ngOnInit() {
  }

  onSubmit()
  {
    this.authService.login(this.usernameBufferForm,this.passwordBufferForm)
      .subscribe(data => {
        this.userService.initUser();
        this.router.navigate(['/']);
      },
      error => {
      });
  }

}
