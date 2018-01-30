import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {AuthService} from "../../shared/services/auth/auth.service";

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) {}

  canActivate(): boolean {
    if (this.authService.currentUser) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
