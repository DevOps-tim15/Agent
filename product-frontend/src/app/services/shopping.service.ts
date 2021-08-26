import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private baseUrl = "http://localhost:8080/api/shopping/";
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }

  createOrder(order: object): Observable<object> {
    return this.http.post<object>(this.baseUrl + "create", order, { headers: this.headers });
  }
}
