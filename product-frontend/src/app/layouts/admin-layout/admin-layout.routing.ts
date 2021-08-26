import { Routes } from "@angular/router";

import { ProductsComponent } from "src/app/pages/products/products.component";
import { NewProductComponent } from "src/app/pages/new-product/new-product.component";
import { EditProductComponent } from "src/app/pages/edit-product/edit-product.component";
import { ProductShoppingComponent } from "src/app/pages/product-shopping/product-shopping.component";

export const AdminLayoutRoutes: Routes = [
  { path: "products", component: ProductsComponent },
  { path: "new-product", component: NewProductComponent },
  { path: "product/:id", component: EditProductComponent },
  { path: "product-shopping", component: ProductShoppingComponent },
  
];
