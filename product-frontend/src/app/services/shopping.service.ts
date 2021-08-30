import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private baseUrl = `${environment.apiUrl}/api/shopping/`;
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
