import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private baseUrl = `${environment.apiUrl}/api/report/`;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private http: HttpClient) { }
  
  porductEarnings(): Observable<Object[]>{
    return this.http.get<Object[]>(this.baseUrl + "productEarnings",{ headers: this.headers });
  }

  productTotalCount(): Observable<Object[]>{
    return this.http.get<Object[]>(this.baseUrl + "productCount",{ headers: this.headers });
  }
  
}
