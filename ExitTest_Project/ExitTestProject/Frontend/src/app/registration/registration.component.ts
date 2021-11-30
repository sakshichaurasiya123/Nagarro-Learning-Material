import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../model/User';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User = {
    emailId: "",
    firstName: "",
    lastName: "",
    password: ""
  }
  message = "";
  constructor(private service: RegistrationService, private router: Router) { }

  ngOnInit(): void {
  }
  //This method is used for userRegistration
  userRegistration() {
    console.log(this.user)
    this.service.userRegistrationFromRemote(this.user).subscribe(data => {

      console.log(this.user)
      alert("You have been registered successfully")
      this.router.navigate(['/login'])
    },
      error => {
        console.log("exception occur")
        this.message = "this email id already exits"
      }
    )
  }
}
