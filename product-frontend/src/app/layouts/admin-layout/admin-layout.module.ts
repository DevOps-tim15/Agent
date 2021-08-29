import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';

import { AdminLayoutRoutes } from "./admin-layout.routing";
import { ProductsComponent } from "src/app/pages/products/products.component";
import { NewProductComponent } from "src/app/pages/new-product/new-product.component";
import { EditProductComponent } from "src/app/pages/edit-product/edit-product.component";

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ProductShoppingComponent } from "src/app/pages/product-shopping/product-shopping.component";
import { ReportComponent } from "src/app/pages/report/report.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ChartsModule,
    ReactiveFormsModule
  ],
  declarations: [
    ProductsComponent,
    NewProductComponent,
    EditProductComponent,
    ProductShoppingComponent,
    ReportComponent
  ],
  exports: [ ChartsModule ]
})
export class AdminLayoutModule {}
