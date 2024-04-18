import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Product } from '../../components/shared/products/product';
import { environment } from '../../../environments/environment.development';
import { ProductCreateRequest } from '../../components/shared/products/product-form/productCreateRequest';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.urlLocalApi}/products/forsale`);
  }

  createProduct(data: ProductCreateRequest): Observable<any> {
    return this.http.post<any>(`${environment.urlLocalApi}/products`, data)
      .pipe(
        tap( (response) => {
          console.log(response);
        }),
        catchError(this.handleError)
      );
  }

  private handleError(error:HttpErrorResponse) {
    if (error.status===0) {
      console.error("Se ha producido un error", error.error);
    } else {
      console.error("Error", error);
    }
    return throwError( () => new Error('Algo falló. Por favor, inténtelo de nuevo.'))
  }

}
