import { Category } from "./category";

export interface Product {
    idProduct?: any;
    nameProduct: string;
    descriptionProduct: string;
    costPriceProduct: string;
    amountProduct: string;
    dateCreatedProduct: any;
    category: Category;
}

export interface ProductDTO {
    idProduct?: any;
    nameProduct: string;
    descriptionProduct: string;
    costPriceProduct: string;
    amountProduct: string;
    dateCreatedProduct: any;
    idCategory: any;
}
