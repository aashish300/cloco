import {inject} from "@angular/core";
import {CanActivateFn, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

export const authGuard: CanActivateFn = (route) => {
  const router: Router = inject(Router);
  const authService = inject(AuthService);
  authService.init();
  const token = authService.token();
  console.log(route.data['roles']);
  console.log(authService.currentUserRole());

  const isAccess = route.data["roles"].includes(authService.currentUserRole());
  console.log(isAccess);

  if (token && isAccess) {
    return true;
  }

  console.log('navigate ')

  // router.navigate(['/login']);

  return router.navigate(['login']);

}
