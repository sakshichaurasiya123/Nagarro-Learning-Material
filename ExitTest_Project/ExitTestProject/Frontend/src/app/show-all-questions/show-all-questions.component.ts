import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { question } from 'ngx-bootstrap-icons';
import { Subject } from 'rxjs';
import { DashboardService } from '../dashboard.service';
import { Comments } from '../model/Comments';
import { Product } from '../model/Product';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-show-all-questions',
  templateUrl: './show-all-questions.component.html',
  styleUrls: ['./show-all-questions.component.css']
})
export class ShowAllQuestionsComponent implements OnInit {

  productList:Array<Product>=[]
 id:any;
 userName:string="";
 product:Product={
  messageBody:"",
  subject:"",
  question:"",
  userName:"",
  date:""
 }

  constructor(private service:DashboardService,private router:Router,private registrationService:RegistrationService,private route:ActivatedRoute) { }

  ngOnInit(): void {

    this.id=this.route.snapshot.paramMap.get('userName');
    if(this.registrationService.getloginUserName()!=null){
      this.userName=this.registrationService.getloginUserName()
    }
    this.allQuestions(this.userName);
    this.getProduct(this.userName);
  }
  
  //This method is used to get all Questions of product
  allQuestions(userName:any){
   
    
    this.service.getQuestionsByUserName(userName).subscribe((data)=>{
    this.productList=data;
     },
     (error)=>
     console.log(error))
  }
  //This method is used to get product 
 getProduct(userName:any){
  this.service.getProductById(userName).subscribe((data)=>{
  this.product=data;
  },(error)=>{
  console.log(error)
})

 }

 //For Logout
 logOutLogin() {

  if(confirm("Are you sure to Logout ?")) { 
    this.registrationService.logOut();
    // location.reload();
     this.router.navigate(['/home']);
 }
 
}
}
