import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './model/Product';
import { Comments } from './model/Comments';


@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  getProductByUserName: any;

  constructor(private http: HttpClient) { }

  readonly url = "http://localhost:8090";

  //This method is used to get list of product by using  get request on api
  public getProductFromRemote(code: String): Observable<any> {
    return this.http.get<any>(this.url + '/getProductBySearch' + '/' + code);
  }

  //This method is used to store product in database by using post request on api
  public postProduct(product: Product): Observable<{}> {
    return this.http.post<Product>(this.url + '/insertProduct', product);
  }

  //This method is used to store comment in databases with product id by using put request on api
  public putComment(review: Comments, code: string): Observable<{}> {
    return this.http.put<{}>(this.url + '/insertComment' + '/' + code, review);
  }
  
  //This method  is used to  get all comment list with their product id  by using  get request on api
  public getcommentList(code: string): Observable<Array<Comments>> {
    return this.http.get<Array<Comments>>(this.url + '/getAllComment' + '/' + code);
  }
  

  //This method is used to get product by using get request on api
  public getProductById(code: String) {
    return this.http.get<any>(this.url + '/getProductById' + '/' + code);
  }

  //This is for getting all questions raised by specific user using get request api
  public getQuestionsByUserName(userName: string): Observable<Array<Product>> {
    return this.http.get<Array<Product>>(this.url + '/getAllQuestions' + '/' + userName);
  }
}

