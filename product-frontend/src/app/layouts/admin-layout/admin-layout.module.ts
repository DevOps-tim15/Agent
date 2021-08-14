import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AdminLayoutRoutes } from "./admin-layout.routing";
import { ProductsComponent } from "src/app/pages/products/products.component";
import { NewProductComponent } from "src/app/pages/new-product/new-product.component";
import { EditProductComponent } from "src/app/pages/edit-product/edit-product.component";

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule
  ],
  declarations: [
    ProductsComponent,
    NewProductComponent,
    EditProductComponent
  ]
})
export class AdminLayoutModule {}
