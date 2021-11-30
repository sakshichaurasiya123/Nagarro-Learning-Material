import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../registration.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
 
  userName:any=""
  constructor(private service:RegistrationService) { }

  ngOnInit(): void {
  this.userName=this.service.getloginUserName();
  }

}
