import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

export const loginGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  authService.init();
  const router = inject(Router);
  const token = authService.token();

  if (!token) {
    console.log('No token found.');
    return true; // navigate to login page if not logged in
  } else {
    return false;
  }

  console.log('token found');

  // if (authService.currentUserRole() === 'super_admin') {
  //   router.navigate(['/user']);
  // } else if (authService.currentUserRole() === 'artist_manager') {
  //   router.navigate(['/artist']);
  // } else {
  // }
  // return router.navigate(['/artist']);
};
