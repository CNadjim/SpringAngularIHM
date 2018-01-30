import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthService} from "../../shared/services/auth/auth.service";

@Injectable()
export class AdminGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.currentUser) {
      if (JSON.stringify(this.authService.currentUser.authorities).search('ROLE_ADMIN') !== -1) {
        return true;
      } else {
        this.router.navigate(['/403']);
        return false;
      }

    } else {
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
      return false;
    }
  }
}

