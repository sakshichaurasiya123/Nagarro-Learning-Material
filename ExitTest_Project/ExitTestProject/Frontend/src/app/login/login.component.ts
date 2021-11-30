import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = {
    emailId: "",
    password: ""
  }
  message = "";
  constructor(private service: RegistrationService, private router: Router) { }

  ngOnInit(): void {
    console.log(this.service.isLoggedIn());
  }

  //This method is used for user login
  loginUser() {
    console.log(this.user);
    this.service.loginUserFromRemote(this.user).subscribe(data => {
      console.log(data);
      this.service.loginUser(data.token, data.userName);
      this.router.navigate(["dashboard"])
    },
      error => {
        console.log("exception occur")
        console.log(error);
        this.message = "Bad Credential, please enter valid emailId and password"
      })
  }
}
