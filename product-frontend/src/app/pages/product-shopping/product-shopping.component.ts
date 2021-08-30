import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DataService } from 'src/app/services/data.service';
import { ShoppingService } from 'src/app/services/shopping.service';
// import { CustomerDataCardComponent } from './customer-data-card/customer-data-card.component';

@Component({
  selector: 'app-product-shopping',
  templateUrl: './product-shopping.component.html',
  styleUrls: ['./product-shopping.component.scss']
})
export class ProductShoppingComponent implements OnInit{

  // @ViewChild(CustomerDataCardComponent) child;

  orderItems : Array<any> = [];
  order: any = {
    items: [],
    totalPrice : 0
  }
  constructor(private dataService:DataService, private fb: FormBuilder, private router: Router, private toastr: ToastrService, private shoppingService: ShoppingService) { }

  customerForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    address: ['', Validators.required],
  });

  ngOnInit(): void {
    this.dataService.orderItems.subscribe(orderItems => this.orderItems = orderItems);
    this.dataService.changeOrderItems(this.orderItems);
    this.calculateOrded();
  }

  calculateOrded(): void{
    var totalAll = 0;
    this.order = {
      items: [],
      totalPrice : 0
    }
    this.orderItems.forEach(item => {
      var itemForDTO = {
        productID: item.product.id,
        quantity: item.product.amount,
        totalPrice: item.totalPrice
      };
      this.order.totalPrice += item.totalPrice;
      this.order.items.push(itemForDTO);
    });
    this.dataService.changeOrderItems(this.orderItems);
    console.log(this.orderItems);
    console.log(this.order);
  } 
  remove(item:any): void{
    const index: number = this.orderItems.indexOf(item);
    if (index !== -1) {
        this.orderItems.splice(index, 1);
    }
    this.calculateOrded();
  }

  buy(): void{
    console.log(this.order);
    var order2 = this.order;
    order2.firstName = this.customerForm.get('firstName').value;
    order2.lastName = this.customerForm.get('lastName').value;
    order2.address = this.customerForm.get('address').value;
    this.shoppingService.createOrder(this.order).subscribe(
      order => {
        this.toastr.success('Your order has been received!');
        this.order = {};
        this.orderItems = [];
        this.dataService.changeOrderItems(this.orderItems);
        this.router.navigate(['/products']);
      },
      error => {
        this.toastr.error(error.error);
      });
  }
}