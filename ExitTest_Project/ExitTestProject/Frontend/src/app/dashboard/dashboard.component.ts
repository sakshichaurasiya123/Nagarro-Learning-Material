import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { Form } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { Stats } from '../model/Stats';
import { DashboardService } from '../dashboard.service';
import { HomeService } from '../home.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  product: Array<Product> = []

  constructor(private service: RegistrationService, private homeService: HomeService, private router: Router) { }
  userName: string = "";
  stats: Stats = {
    countPosts: 0,
    countComments: 0,
    countUsers: 0
  }
  ngOnInit(): void {
    if (this.service.getloginUserName() != null) {
      this.userName = this.service.getloginUserName()
    }
    this.getStats()
  }
  // This method is used to search the product based on user input  
  searchProduct(searchProduct: any) {
    console.log("product url" + searchProduct);
    this.router.navigate(["productList" + "/" + searchProduct])
  }
  //This method is used to nagvigate on add product component
  newProduct() {
    this.router.navigate(["addProduct"])
  }

  //This method is used to get stats details
  getStats() {
    this.homeService.getStatsFromRemote().subscribe((data) => {
      console.log(data);
      this.stats.countUsers = data.countUsers,
        this.stats.countComments = data.countComments,
        this.stats.countPosts = data.countPosts;
    }, (error) => {
      console.log(error);
    })
  }

  //This method is used for logout user
  logOutLogin() {
    if(confirm("Are you sure to Logout ?")) { 
    this.service.logOut();
    location.reload();
    }
  }
}
