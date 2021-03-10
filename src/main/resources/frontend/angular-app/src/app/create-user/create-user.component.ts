import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UsersService } from "../users.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  constructor(private usersService: UsersService, private router: Router) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.usersService.createUser(this.user).subscribe(data => {
        console.log('data ', data);
        this.goToUsersList();
      },
      error => console.log('error ', error));
  }

  goToUsersList(){
    this.router.navigate(['/users'])
  }

  onSubmit(){
    console.log('user ', this.user);
    this.saveUser();
  }

}
