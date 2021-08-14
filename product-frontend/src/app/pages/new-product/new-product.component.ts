import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.scss']
})
export class NewProductComponent implements OnInit {

  imgFile: string;

  uploadForm = this.fb.group({
    name: ['', Validators.required],
    quantity: ['', Validators.required],
    price: ['', Validators.required],
    image: ['']
  });

  constructor(private fb: FormBuilder, private productService: ProductServiceService, private toastr: ToastrService, private router: Router) { }

  get uf() {
    return this.uploadForm.controls;
  }

  onImageChange(e) {
    const reader = new FileReader();

    if (e.target.files && e.target.files.length) {
      const [file] = e.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {
        this.imgFile = reader.result as string;
        this.uploadForm.patchValue({
          image: reader.result
        });

      };
    }
  }
  ngOnInit(): void {
  }

  addNew(): void {
    let productName: string = this.uploadForm.get('name').value;
    let productPrice: number = this.uploadForm.get('price').value;
    let productQuantity: number = this.uploadForm.get('quantity').value;
    let productImage: string = "image"
    let product = {
      name: productName,
      quantity: productQuantity,
      price: productPrice,
      picture: productImage
    }
    console.log(product)
    this.productService.createProduct(product).subscribe(
      product => {
        this.toastr.success('You have successfully added a new product');
        this.router.navigate(['/products']);
      },
      error => {
        this.toastr.error(error.error);

      }
    );
  }

}
