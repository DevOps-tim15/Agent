import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})

export class ProductsComponent implements OnInit {

  public productList:object[] = [];

  constructor(private productService: ProductServiceService, private toastr: ToastrService, private router: Router, private route: ActivatedRoute,) { }

  ngOnInit(): void {
    this.getProducts()
  }

  delete(id: number): void {
    this.productService.removeProduct(id).subscribe(
      () => {
        this.toastr.success('You have successfully removed product');
        this.productList.splice(this.productList.indexOf(this.productList.find(m => m['id'] === id)), 1);
        // window.location.reload();
      },
      error => {
        console.log(error.error)

      }
    )
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(productList => this.productList = productList);
  }
}
