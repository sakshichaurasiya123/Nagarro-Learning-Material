import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { Router,ActivatedRoute } from '@angular/router';
import { Product } from '../model/Product';
import { RegistrationService } from '../registration.service';
import { Comments } from '../model/Comments';
@Component({
  selector: 'app-commentList',
  templateUrl: './commentList.component.html',
  styleUrls: ['./commentList.component.css']
})
export class commentListComponent implements OnInit {
 commentList:Array<Comments>=[]
 id:any;
 userName:string=""
 product:Product={
  messageBody:"",
  subject:"",
  question:"",
  userName:"",
  date:""
 }
  constructor(private service:DashboardService,private registrationService:RegistrationService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this. id=this.route.snapshot.paramMap.get('subject');
    if(this.registrationService.getloginUserName()!=null){
      this.userName=this.registrationService.getloginUserName()
    }
    this.allComment(this.id);
    this.getProduct(this.id);
  }
  
  //This method is used to get all Comment of product
  allComment(id:any){
   
    console.log(id); 
    this.service.getcommentList(id).subscribe((data)=>{
    this.commentList=data;
     },
     (error)=>
     console.log(error))
  }
  //This method is used to get product 
 getProduct(id:any){
  this.service.getProductById(id).subscribe((data)=>{
  this.product=data;
  console.log(this.product);
  },(error)=>{
  console.log(error)
})

 }

 //For Logout
 logOutLogin(){
  if(confirm("Are you sure to Logout ?")) { 
  this.registrationService.logOut();
  location.reload();
    }
  }
}
