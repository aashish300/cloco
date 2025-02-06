import {inject} from "@angular/core";
import {CanActivateFn, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

export const authGuard: CanActivateFn = (route) => {
  const router: Router = inject(Router);
  const authService = inject(AuthService);
  authService.init();
  const token = authService.token();

  const isAccess = route.data["roles"].includes(authService.currentUserRole());

  if (token && isAccess) {
    return true;
  }

  return router.navigate(['login']);

}
