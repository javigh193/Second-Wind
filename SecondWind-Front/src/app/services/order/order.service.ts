import { Injectable } from '@angular/core';
import { OrderRequest } from '../../components/shared/orders/orderRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  //lastOrderData: BehaviorSubject<String> = new BehaviorSubject<String>("");

  constructor(private http: HttpClient) {}

  createOrder(data: OrderRequest): Observable<any> {
    return this.http.post<any>( `${environment.urlLocalApi}/orders`, data)
      .pipe(
        tap( (resp) => {
          console.log(resp);
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
