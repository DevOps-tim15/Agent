export interface Product {
    id: number;
    name: string;
}

export interface Report {
    productDTO: Product;
    total: number;
}