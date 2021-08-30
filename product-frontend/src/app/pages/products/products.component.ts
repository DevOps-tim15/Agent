import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})

export class ProductsComponent implements OnInit {

  public productList:object[] = [];
  public orderItems:Array<any> = [];

  constructor(private productService: ProductServiceService, private toastr: ToastrService,
     private router: Router, private route: ActivatedRoute,private dataService:DataService) { }
  ngOnInit(): void {
    this.getProducts();
    this.dataService.orderItems.subscribe(orderItems => this.orderItems = orderItems);
  }

  delete(id: number): void {
    this.productService.removeProduct(id).subscribe(
      () => {
        this.toastr.success('You have successfully removed product');
        this.productList.splice(this.productList.indexOf(this.productList.find(m => m['id'] === id)), 1);
        // window.location.reload();
      },
      error => {
        this.toastr.error(error.error);
        console.log(error)

      }
    )
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(productList => this.productList = productList);
  }

  addToCart(productOrig:any):void{
    const product = Object.assign({}, productOrig);
    console.log("Product: ", product);
    if((product.amount <= 0) || (product.amount % 1 !== 0) ){
      this.toastr.error("Product quantity must be positive integer number!");
      return;
    }

    var itemFound = null;
    this.orderItems.forEach(item =>{
      if(item.product.id == product.id){
        itemFound = item;
        return;
      }
    })
    if(itemFound == null){
      if(product.quantity < product.amount){
        this.toastr.error('Not enough product');
        return;
      }
      var item = {
        product: product,
        totalPrice : product.amount * product.price
      }
      this.orderItems.push(item);
    }
    else{
      if(itemFound.product.quantity < itemFound.product.amount+product.amount){
        this.toastr.error('Not enough product');
        return;
      }
      itemFound.product.amount += product.amount;
      itemFound.totalPrice = itemFound.product.amount * itemFound.product.price;
    }

    this.dataService.changeOrderItems(this.orderItems);
    this.toastr.success("Product added to cart!");
    
  }
}
