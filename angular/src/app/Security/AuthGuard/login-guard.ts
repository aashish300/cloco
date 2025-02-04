import {SecurityService} from "../security.service";
import {inject} from "@angular/core";
import {CanActivateFn, Router} from "@angular/router";

export const loginGuard: CanActivateFn = () => {
  const securityService = inject(SecurityService);
  const router = inject(Router);
  const token = securityService.getFromLocalStorage('token');
  console.log('Token:', token);

  if (token) {
    return router.parseUrl('/dashboard'); // Redirect to login if no token
  }

  return true; // Allow access if token exists
}
