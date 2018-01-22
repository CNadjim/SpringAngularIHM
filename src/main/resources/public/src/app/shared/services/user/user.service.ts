import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {UserModel} from '../../models/user/user.model';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<any> {
    return this.http.get('/api/user/');
  }

}
