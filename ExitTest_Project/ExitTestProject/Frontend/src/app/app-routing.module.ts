import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddproductComponent } from './addproduct/addproduct.component';
import { addcommentComponent } from './addcomment/addcomment.component';
import { AuthenticationGuard } from './authentication.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductlistComponent } from './productlist/productlist.component';
import { RegistrationComponent } from './registration/registration.component';
import { commentListComponent } from './commentList/commentList.component';
import { ShowAllQuestionsComponent } from './show-all-questions/show-all-questions.component';

const routes: Routes = [
  {path:"login",component:LoginComponent},
  {path:"registration",component:RegistrationComponent},
  {path:"dashboard",component:DashboardComponent,canActivate:[AuthenticationGuard]},
  {path:"home",component:HomeComponent},
  {path:"productList/:productSearch",component:ProductlistComponent,canActivate:[AuthenticationGuard]},
  {path:"viewList/:subject",component:commentListComponent,canActivate:[AuthenticationGuard]},
  {path:"addcomment/:subject",component:addcommentComponent,canActivate:[AuthenticationGuard]},
  {path:"addProduct",component:AddproductComponent,canActivate:[AuthenticationGuard]},
  {path:"",redirectTo:'/home',pathMatch:'full'},
  {path:"show-all-questions",component:ShowAllQuestionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
