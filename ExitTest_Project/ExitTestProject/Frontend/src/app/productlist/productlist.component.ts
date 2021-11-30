import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { Product } from '../model/Product';
import { Route,ActivatedRoute } from '@angular/router';
import { RegistrationService } from '../registration.service';
@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.component.html',
  styleUrls: ['./productlist.component.css']
})
export class ProductlistComponent implements OnInit {
  product:Array<Product>=[]
  searchValue:any=""
  userName:string=""
  constructor(private service:DashboardService,private registrationService:RegistrationService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    if(this.registrationService.getloginUserName()!=null){
      this.userName=this.registrationService.getloginUserName()
    }
    this.productList();
   this.productAvailabl()
  }
 
  //This method is used to get the list of product
  productList(){
    const id:any=this.route.snapshot.paramMap.get('productSearch');
  this.service.getProductFromRemote(id).subscribe((data)=>{
    this.product=data;
    if(data.length==0)
    {
      this.productAvailabl();
    }
  },(error)=>{
    console.log(error);
  })
}

//This method is used to logout user
logOutLogin(){
  if(confirm("Are you sure to Logout ?")) { 
  this.registrationService.logOut();
  location.reload();
    } 
  }

  //This method is used to check the
  productAvailabl(){
    if(this.product.length==0)
    {
      return true;
    }
    return false;
  }
}
