
import {Injectable, Injector} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpClient
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {AuthService} from "../auth/auth.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = localStorage.getItem("TOKEN_KEY");

    if (idToken){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${idToken}`
        }
      });
    }else{
      request = request.clone();
    }

    return next.handle(request);


  }
}
