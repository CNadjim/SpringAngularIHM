import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {UserModel} from '../../models/user/user.model';
import {AuthService} from "../auth/auth.service";
import {UserTokenModel} from "../../models/auth/user.token.model";

@Injectable()
export class UserService {


  currentUser : UserModel;
  currentUserToken : UserTokenModel;


  constructor(
    private authService:AuthService,
    private http: HttpClient) {
  }

  public getUsers(): Observable<any> {
    return this.http.get('/api/user/all');
  }

  public addUser(user) : Observable<any> {
    return this.http.post('api/user/add',user);
  }

  public getUserById(id) : Observable<any> {
    return this.http.get('api/user/'+id);
  }

  public getMyInfo() : Observable<any> {
    return this.http.get('api/user/whoami');
  }


  initUser() {
    const promise = this.authService.refresh().toPromise()
      .then(data => {
        this.currentUserToken = data;
        if (this.currentUserToken.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }


}
