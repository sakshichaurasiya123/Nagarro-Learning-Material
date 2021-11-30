import { Component, OnInit } from '@angular/core';
import { HomeService } from '../home.service';
import { Stats } from '../model/Stats';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  stats: Stats = {
    countPosts: 0,
    countComments: 0,
    countUsers: 0
  }

  constructor(private service: HomeService) { }

  ngOnInit(): void {
    this.getStats();
  }
  
  //This method is used to get stats details
  getStats() {
    this.service.getStatsFromRemote().subscribe((data) => {
      console.log(data);
      this.stats.countUsers = data.countUsers,
        this.stats.countComments = data.countComments,
        this.stats.countPosts = data.countPosts;
    }, (error) => {
      console.log(error);
    })
  }
}
