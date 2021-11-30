import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { Comments } from '../model/Comments';
import { Form } from '@angular/forms';
import { Route, ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { Product } from '../model/Product';
@Component({
  selector: 'app-addcomment',
  templateUrl: './addcomment.component.html',
  styleUrls: ['./addcomment.component.css']
})
export class addcommentComponent implements OnInit {
   product: Product = {
    messageBody: "",
    subject: "",
    question: "",
    userName: "",
    date: ""
  }
 
 comment: Comments = {
    commentId: "",
    comment: "",
    userName: "",
    heading: "",
    date: ""
  }

  constructor(private dashBoardservice: DashboardService, private registrationService: RegistrationService, private route: ActivatedRoute, private router: Router) { }
  id: any
  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('subject');
    if (this.registrationService.getloginUserName() != null) {
      this.comment.userName = this.registrationService.getloginUserName()
    }
    this.getProduct()
  }
  //This method  used to get product
  getProduct() {
    this.dashBoardservice.getProductById(this.id).subscribe((data) => {
      this.product = data;
    }, (error) => {
      console.log(error)
    })
  }


  //This method is used to get  Comments from client side and store into  database through api
  getFormData() {
      this.dashBoardservice.putComment(this.comment, this.id).subscribe((data) => {
        alert("Your comment has been added successfully");
        this.router.navigate(["/productList/" + this.id])
      }, (error) => {
        console.log(error)
        this.ngOnInit();
      }) 
  }

  //This method is used for logout user
  logOutLogin() {
    if(confirm("Are you sure to Logout ?")) { 
  
    this.registrationService.logOut();
    location.reload();
  }
}
}