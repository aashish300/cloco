import {SecurityService} from "../security.service";
import {inject} from "@angular/core";
import {Router} from "@angular/router";

export const loginGuard = () => {
  const token = SecurityService.getFromLocalStorage('token');
  console.log('token')
  console.log(token)
  if(!token) return false;
  return inject(Router).navigate(['/dashboard']);
}
