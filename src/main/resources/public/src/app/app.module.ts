import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';


import { AppComponent } from './app.component';
import {ServicesModule} from './shared/services/services.module';

import { ROUTES } from './app.routes';
import {PreloadAllModules, RouterModule} from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './routes/home/home.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { UserComponent } from './routes/user/user.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { LoginComponent } from './routes/login/login.component';
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './layout/header/header.component';
import {AppMaterialModule} from "./shared/components/materialModule/app.material.module";
import {SidenavService} from "./shared/services/sideNav/sidenav.service";
import {GuardsModule} from "./guards/guards.module";
import {TokenInterceptor} from "./shared/services/http/TokenInterceptor";
import {AuthService} from "./shared/services/auth/auth.service";
import { TodoItemComponent } from './routes/todo-item/todo-item.component';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/", ".json");
}
export function initUserFactory(authService: AuthService) {
    return () => authService.refreshUser();
}


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    LoginComponent,
    HeaderComponent,
    TodoItemComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    GuardsModule,
    ServicesModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES, {useHash: true, preloadingStrategy: PreloadAllModules}),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initUserFactory,
      deps: [AuthService],
      multi: true
    }
    ,
    SidenavService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
