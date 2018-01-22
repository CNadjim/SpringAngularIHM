import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {UserModel} from '../../shared/models/user/user.model';
import {UserService} from '../../shared/services/user/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  dataSource: MatTableDataSource<any>;
  constructor(private userService: UserService) {
    this.getAllUser();
  }

  ngOnInit() {
  }

  public getAllUser() {
    this.userService.getUsers().subscribe(data => {
      this.dataSource = new MatTableDataSource<any>(data);
    });
  }

}
