import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginRequestModel} from "../../shared/models/auth/login.request.model";
import {Subject} from "rxjs/Subject";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usernameBufferForm : string;
  passwordBufferForm : string;
  loginRequest: LoginRequestModel;



  constructor(

    private router: Router,
    private route: ActivatedRoute,
    private authService : AuthService
  ) { }

  ngOnInit() {}

  onSubmit()
  {
    this.loginRequest = new LoginRequestModel();
    this.loginRequest.password = this.passwordBufferForm;
    this.loginRequest.username = this.usernameBufferForm;

    this.authService.login(this.loginRequest)
      .subscribe(data => {
        localStorage.setItem('TOKEN_KEY', data.token);
        this.authService.initUser();
        this.router.navigate(['/']);
      },
      error => {
      });

  }

}
