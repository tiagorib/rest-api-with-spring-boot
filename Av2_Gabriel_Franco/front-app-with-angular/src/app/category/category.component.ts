import { Component, OnInit, ViewChild } from '@angular/core';
import { Category } from '../model/category';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { CategoryService } from '../service/category.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  success: boolean = false;
  errors!: String[];
  displayedColumns: string[] = ['idCategory', 'nameCategory', 'descriptionCategory', 'deleteCategory', 'findCategory'];
  ELEMENT_DATA: Category[] = [];
  message: string = '';
  dataSource = new MatTableDataSource<Category>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild('categoryForm') categoryForm!: NgForm;

  constructor(
    private service: CategoryService
  ) { }

  ngOnInit(): void {
    this.listCategory();
  }

  category: Category = {
    idCategory: '',
    nameCategory: '',
    descriptionCategory: ''
  }

  saveCategory() {
    if (this.category.idCategory) {
      this.service.update(this.category).subscribe((response: any) => {
        this.success = true;
        this.errors = [];
        this.category = response.result as Category;
        this.listCategory();
        this.emptyForm();
      });
    } else {
      this.service.save(this.category).subscribe((response: any) => {
        this.success = true;
        this.errors = [];
        this.category = response.result as Category;
        this.listCategory();
        this.emptyForm();
      });
    }
  }

  listCategory() {
    this.service.list().subscribe((response: any) => {
      this.ELEMENT_DATA = response.result as Category[];
      this.dataSource = new MatTableDataSource<Category>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });

  }

  deleteCategory(category: Category) {
    if (window.confirm('Deseja realmente excluir esta categoria?')) {
      this.service.delete(category.idCategory).subscribe((response: any) => {
        this.message = response.result.result as string;
        window.alert(this.message);
        this.listCategory();
      });
    }
  }

  findCategory(category: Category) {
    this.service.findById(category.idCategory).subscribe((response: any) => {
      this.category = response.result as Category;
      this.success = false;
    });
  }

  emptyForm() {
    this.categoryForm.resetForm();
    this.category.idCategory = '';
  }

}