import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { DashboardService } from '../dashboard.service';
import { Router } from '@angular/router';
import { Form } from '@angular/forms';
import { RegistrationService } from '../registration.service';
@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {
  message = ""
  product: Product = {
    messageBody: "",
    subject: "",
    question: "",
    userName: "",
    date: ""
  }

  constructor(private dashboardService: DashboardService, private registrationService: RegistrationService, private router: Router) { }

  ngOnInit(): void {
    if (this.registrationService.getloginUserName() != null) {
      this.product.userName = this.registrationService.getloginUserName()
    }
  }
  //This method is used to add product  
  addNewProduct() {
    this.product.subject = this.product.subject.replace(/ /g, "")
    
    this.dashboardService.postProduct(this.product).subscribe(() => {
      alert("Your product has been added successfully");
      this.router.navigate(["/dashboard"])
    },
      (error) => {
        this.message = "This subject already present and Please! wait for 30 sec to redirect to productView page"
        setTimeout(() => {
          this.router.navigate(["/productList/" + this.product.subject])
        }, 3000);
      })

  }

  //This method is used for logout User from application
  logOutLogin() {
    if(confirm("Are you sure to Logout ?")) { 
    this.registrationService.logOut();
    location.reload();
  }
}
}
