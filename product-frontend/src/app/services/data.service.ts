import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private orderData = new BehaviorSubject(Array<any>());
  orderItems = this.orderData.asObservable();

  constructor() { }

  changeOrderItems(orderItems: Array<any>) {
    this.orderData.next(orderItems);
  }
}
