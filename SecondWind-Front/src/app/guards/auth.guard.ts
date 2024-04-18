import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { inject } from '@angular/core';
import { MessagesService } from '../services/messages.service';

export const authGuard: CanActivateFn = (route, state) => {

  const loginService = inject(LoginService);
  const router = inject(Router);
  const messagesService = inject(MessagesService);

  let isAuthenticated:boolean = false;

  loginService.currentUserLoginOn.subscribe(
    {
      next:(userLoginOn) => {
        isAuthenticated=userLoginOn;
      }
    }
  );

  if (isAuthenticated) {
    return true;
  } else {
    messagesService.clearMessages();
    messagesService.sendMessage('Por favor, inicie sesión para poder acceder.');
    return router.parseUrl('/iniciar-sesion');
  }

};
