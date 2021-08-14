import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ProductServiceService } from 'src/app/services/product-service.service';
@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {
  
  form = this.fb.group({
    name: ['', Validators.required],
    quantity: ['', Validators.required],
    price: ['', Validators.required],
    picture: ['']
  });

  id: number;
  constructor(private fb: FormBuilder, private productService : ProductServiceService,private toastr: ToastrService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.getProduct()
  }

  getProduct(): void{
    this.route.paramMap.subscribe(
      pmap => {
        this.id = +pmap.get('id');
        this.productService.getProduct(this.id).subscribe(
          product => {
            this.form.controls['name'].setValue(product['name'])
            this.form.controls['quantity'].setValue(product['quantity'])
            this.form.controls['price'].setValue(product['price'])
            console.log(product)
          },
          error => {
            console.log(error.error);
          }
        );
      }
    );
  }

  onSubmit(): void {
    let productName: string = this.form.get('name').value;
    let productPrice: number = this.form.get('price').value;
    let productQuantity: number = this.form.get('quantity').value;
    let productImage: string = "image"
    let product = {
      name: productName,
      quantity: productQuantity,
      price: productPrice,
      picture: productImage
    }
    console.log(product)

    this.productService.editProduct(this.id, product).subscribe(
      () => {
        this.toastr.success('You have successfully modified the product');
        this.router.navigate(['/products']);
      },
      error => {
        this.toastr.error(error.error);
      }
    );
  }

}
