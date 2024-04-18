import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { RegisterRequest } from './registerRequest';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<string> = new BehaviorSubject<string>("");

  constructor(private http: HttpClient) { }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post<any>( `${environment.urlLocalApi}/auth/authenticate`, credentials)
      .pipe(
        tap( (userData) => {
          sessionStorage.setItem("token", userData.token)
          this.currentUserData.next(userData.token);
          this.currentUserLoginOn.next(true);
        }),
        map((userData) => userData.token),
        catchError(this.handleError)
      );
  }

  register(credentials: RegisterRequest): Observable<any> {
    console.log(credentials);
    return this.http.post<any>( `${environment.urlLocalApi}/auth/register`, credentials)
      .pipe(
        tap( (userData) => {
          sessionStorage.setItem("token", userData.token)
          this.currentUserData.next(userData.token);
          this.currentUserLoginOn.next(true);
        }),
        map((userData) => userData.token),
        catchError(this.handleError)
      );
  }

  logout(): void {
    sessionStorage.removeItem("token");
    this.currentUserLoginOn.next(false);
    this.currentUserData.next("");
  }

  private handleError(error:HttpErrorResponse) {
    if (error.status===0) {
      console.error("Se ha producido un error", error.error);
    } else {
      console.error("Error", error);
    }
    return throwError( () => new Error('Algo falló. Por favor, inténtelo de nuevo.'))
  }

  get userData(): Observable<string> {
    return this.currentUserData.asObservable();
  }

  get userLoginOn(): Observable<boolean> {
    return this.currentUserLoginOn.asObservable();
  }

}
