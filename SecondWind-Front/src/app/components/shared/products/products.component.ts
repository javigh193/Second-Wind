import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductService } from '../../../services/product/product.service';
import { EMPTY } from 'rxjs/internal/observable/empty';
import { AsyncPipe, CurrencyPipe, DatePipe, NgOptimizedImage, SlicePipe, TitleCasePipe } from '@angular/common';
import { JwtService } from '../../../services/jwt/jwt.service';
import { OrderService } from '../../../services/order/order.service';
import { OrderRequest } from '../orders/orderRequest';
import { Observable, catchError } from 'rxjs';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [NgOptimizedImage, TitleCasePipe, CurrencyPipe, SlicePipe, DatePipe, AsyncPipe],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

    public products$!: Observable<Product[]>;
    public errorMessage!: string;

    constructor(
      private jwtService: JwtService, 
      private productService: ProductService,
      private orderService: OrderService
    ) {}

    ngOnInit(): void {
      this.loadProducts();
    }

    buy(productId: number) {
      let jwt: any = sessionStorage.getItem("token");
      let email = this.jwtService.getClaim(jwt, 'sub');
      let data: OrderRequest = {productId: productId, buyerEmail: email};
      this.orderService.createOrder(data).subscribe(
        {
          next: (response) => {
            console.log(response)
            this.loadProducts();
          },
          error: (error) => {
            console.log(error);
          },
          complete: () => {}
        }
      );
    }

  loadProducts() {
    this.products$ = this.productService.getProducts()
      .pipe(catchError( (error: string) => {
        this.errorMessage = error;
        return EMPTY;
    }))
  }
}
