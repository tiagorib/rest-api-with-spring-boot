import { Component, ViewChild } from '@angular/core';
import { Product, ProductDTO } from '../model/product';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ProductService } from '../service/product.service';
import { DatePipe } from '@angular/common';
import { Category } from '../model/category';
import { CategoryService } from '../service/category.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  success: boolean = false;
  errors!: String[];
  displayedColumns: string[] = ['idProduct', 'nameProduct', 'descriptionProduct', 'costPriceProduct', 'amountProduct', 'dateCreatedProduct', 'category', 'deleteProduct', 'findProduct'];
  ELEMENT_DATA: Product[] = [];
  message: string = '';
  dataSource = new MatTableDataSource<Product>(this.ELEMENT_DATA);

  categories: Category[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild('productForm') productForm!: NgForm;

  constructor(
    private service: ProductService,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.listProduct();
    this.categoryService.list().subscribe((response: any) => {
      this.categories = response.result as Category[];
    });
  }

  category: Category = {
    idCategory: '',
    nameCategory: '',
    descriptionCategory: ''
  }

  product: Product = {
    idProduct: '',
    nameProduct: '',
    descriptionProduct: '',
    costPriceProduct: '',
    amountProduct: '',
    dateCreatedProduct: '',
    category: {
      idCategory: ''
    }
  }

  productDTO: ProductDTO = {
    idProduct: '',
    nameProduct: '',
    descriptionProduct: '',
    costPriceProduct: '',
    amountProduct: '',
    dateCreatedProduct: '',
    idCategory: ''
  }

  saveProduct() {
    const datePipe = new DatePipe('en-US');
    this.productDTO.dateCreatedProduct = datePipe.transform(this.productDTO.dateCreatedProduct, 'dd/MM/yyyy');
    if (this.productDTO.idProduct) {
      this.product.idProduct = this.productDTO.idProduct;
      this.product.nameProduct = this.productDTO.nameProduct;
      this.product.descriptionProduct = this.productDTO.descriptionProduct;
      this.product.costPriceProduct = this.productDTO.costPriceProduct;
      this.product.amountProduct = this.productDTO.amountProduct;
      this.product.dateCreatedProduct = this.productDTO.dateCreatedProduct;
      this.product.category.idCategory = this.productDTO.idCategory;
      this.service.update(this.product).subscribe((response: any) => {
        this.success = true;
        this.errors = [];
        this.product = response.result as Product;
        var date = this.product.dateCreatedProduct;
        var newDate = date.split("/").reverse().join("-");
        this.product.dateCreatedProduct = newDate;
        this.listProduct();
        this.emptyForm();
      });
    } else {
      this.service.save(this.productDTO).subscribe((response: any) => {
        this.success = true;
        this.errors = [];
        this.product = response.result as Product;
        var date = this.product.dateCreatedProduct;
        var newDate = date.split("/").reverse().join("-");
        this.product.dateCreatedProduct = newDate;
        this.listProduct();
        this.emptyForm();
      });
    }
  }

  listProduct() {
    this.service.list().subscribe((response: any) => {
      this.ELEMENT_DATA = response.result as Product[];
      this.dataSource = new MatTableDataSource<Product>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });

  }

  deleteProduct(product: Product) {
    if (window.confirm('Deseja realmente excluir este produto?')) {
      this.service.delete(product.idProduct).subscribe((response: any) => {
        this.message = response.result.result as string;
        window.alert(this.message);
        this.listProduct();
      });
    }
  }

  findProduct(product: Product) {
    this.service.findById(product.idProduct).subscribe((response: any) => {
      this.product = response.result as Product;
      this.productDTO.idProduct = this.product.idProduct;
      this.productDTO.nameProduct = this.product.nameProduct;
      this.productDTO.descriptionProduct = this.product.descriptionProduct;
      this.productDTO.costPriceProduct = this.product.costPriceProduct;
      this.productDTO.amountProduct = this.product.amountProduct;
      var date = this.product.dateCreatedProduct;
      var newDate = date.split("/").reverse().join("-");
      this.productDTO.dateCreatedProduct = newDate;
      this.productDTO.idCategory = this.product.category?.idCategory;
      this.success = false;
    });
  }

  emptyForm() {
    this.productForm.resetForm();
    this.productDTO.idProduct = '';
  }
}
