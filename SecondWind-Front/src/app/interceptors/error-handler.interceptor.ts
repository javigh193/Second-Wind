import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {

  const router: Router = inject(Router);

  return next(req).pipe(
    catchError((err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          sessionStorage.removeItem('token');
          console.error('Unauthorized request:', err);
          router.navigateByUrl('/iniciar-sesion');
        } else if (err.status === 403) {
          sessionStorage.removeItem('token');
          console.error('Forbidden request:', err);
          router.navigateByUrl('/iniciar-sesion');
        } else {
          console.error('HTTP error:', err);
        }
      } else {
        console.error('An error occurred:', err);
      }
      return throwError(() => err); 
    })
  );
};

