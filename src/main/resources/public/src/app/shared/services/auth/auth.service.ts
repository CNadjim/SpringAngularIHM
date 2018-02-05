import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {LoginRequestModel} from "../../models/auth/login.request.model";
import {UserModel} from "../../models/user/user.model";
import {UserTokenModel} from "../../models/auth/user.token.model";

@Injectable()
export class AuthService {

  currentUser;


  constructor(private http: HttpClient) {
  }

  public login(loginRequest: LoginRequestModel): Observable<any> {
    return this.http.post('/api/auth/login',loginRequest);
  }

  public refresh(): Observable<any> {
    return this.http.get('/api/auth/refresh');
  }

  public getCurrentUser() : Observable<any> {
    return this.http.get('api/auth/user');
  }

  public logout(): void{
    localStorage.removeItem('TOKEN_KEY');
  }


  refreshUser() {
    const promise = this.refresh().toPromise()
      .then(res => {
        if (res !== null) {
          localStorage.setItem('TOKEN_KEY', res.token);
          return this.getCurrentUser().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }

  initUser(){
    const promise = this.getCurrentUser().toPromise().then(data =>{
      this.currentUser = data;
    }).catch();

    return promise;
  }

  haveAdminAuth() : boolean{
    return JSON.stringify(this.currentUser.authorities).search('ROLE_ADMIN') !== -1 ;
  }

  haveUserAuth() : boolean{
    return JSON.stringify(this.currentUser.authorities).search('ROLE_USER') !== -1 ;
  }


}
