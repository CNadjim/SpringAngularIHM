import {Component, OnInit, ViewChild} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {UserService} from "../../shared/services/user/user.service";
import {AuthService} from "../../shared/services/auth/auth.service";
import {Router} from "@angular/router";
import {MatSidenav} from "@angular/material";
import {SidenavService} from "../../shared/services/sideNav/sidenav.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  translate : TranslateService;

  constructor(
    private router: Router,
    private sidenavService: SidenavService,
    private userService : UserService,
    private authService: AuthService,
    private translateService: TranslateService) {

    this.translate = translateService;
    this.translateService.addLangs(["English", "Français"]);
    this.translate.setDefaultLang('English');
    let browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/English|Français/) ? browserLang : 'English');
  }

  ngOnInit() {
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

  onLogout(){
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/']);
      this.userService.currentUser = null;
    });
  }

  openSidenav() {
    this.sidenavService.toggle();
  }



}
