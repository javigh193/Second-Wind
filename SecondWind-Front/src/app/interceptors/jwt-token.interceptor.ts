import { HttpInterceptorFn } from '@angular/common/http';

export const jwtTokenInterceptor: HttpInterceptorFn = (req, next) => {

  const token = sessionStorage.getItem('token');
  if (token) {
    const cloneRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloneRequest);
  } else {
    return next(req);
  }
};
