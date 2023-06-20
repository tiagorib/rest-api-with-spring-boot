import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './category/category.component';
import { CustomerComponent } from './customer/customer.component';
import { ProductComponent } from './product/product.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'category', component:CategoryComponent},
  {path:'customer', component:CustomerComponent},
  {path:'product', component:ProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
