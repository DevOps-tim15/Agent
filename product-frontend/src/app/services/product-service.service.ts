import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  private baseUrl = "http://localhost:8080/api/product/";
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }

  getProducts(): Observable<object[]>{
    return this.http.get<object[]>(this.baseUrl + "getAll",{ headers: this.headers });
  }

  getProduct(id: number): Observable<object> {
    return this.http.get<object>(this.baseUrl + id, { headers: this.headers });
  }

  createProduct(product: object): Observable<object> {
    return this.http.post<object>(this.baseUrl + "create", product, { headers: this.headers });
  }

  editProduct(id: number, model: object): Observable<object> {
    return this.http.post<object>(this.baseUrl + "update/" + id, model, { headers: this.headers });
  }

  removeProduct(id: number): Observable<string> {
    return this.http.put(this.baseUrl + 'delete/' + id, null, {responseType: 'text'});
  }
}
