import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {


  constructor(private http: HttpClient) {
  }

  public login(username,password): Observable<any> {
    return this.http.post('/api/login',"username="+username+"&password="+password,{headers: new HttpHeaders().set('Content-Type',`application/x-www-form-urlencoded`)});
  }

  public logout(): Observable<any> {
    return this.http.post('/api/logout',{});
  }

  public refresh(): Observable<any> {
    return this.http.get('/api/refresh');
  }

}
