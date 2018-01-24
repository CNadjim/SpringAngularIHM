import {Component, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  @ViewChild('sidenav') sidenav: MatSidenav;

  reason = '';
  translate : TranslateService;
  constructor(translate: TranslateService) {
    this.translate = translate;
    this.translate.addLangs(["English", "Français"]);
    this.translate.setDefaultLang('English');
    let browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/English|Français/) ? browserLang : 'English');
  }

  close(reason: string) {
    this.reason = reason;
    this.sidenav.close();
  }




}
