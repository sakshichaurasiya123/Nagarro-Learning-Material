import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './model/User';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }
  readonly url = "http://localhost:8090";

  //This method is used for login by using post request on api 
  public loginUserFromRemote(user: any): Observable<any> {
    return this.http.post(this.url + '/auth', user);
  }

  //This method is used  for userRegistration  by using  post request on api
  public userRegistrationFromRemote(user: User): Observable<any> {
    return this.http.post(this.url + '/registerUser', user);
  }

  //This method is used to save the userName and token into sessionStorage
  loginUser(token: string, userName: string) {
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("userName", userName)
  }

  //This method is used to check the user login or logout
  isLoggedIn() {
    let token = sessionStorage.getItem("token");
    console.log(token);
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }

  //This method is used to remove the  userName and token  into sessionStorage
  logOut() {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userName")
    return true;
  }

//This method is used to get token
  getToken() {
    let token = sessionStorage.getItem("token");
    console.log(token);
    if (token == undefined || token === '' || token == null) {
      return null;
    } else {
      return token;
    }

  }
 
  //This method is used to get loginUserName
  getloginUserName() {
    let userName = sessionStorage.getItem('userName');
    if (userName == undefined || userName === '' || userName == null) {
      return "please!login";
    } else {
      return userName;
    }
  }
}
