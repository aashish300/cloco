import {Injectable} from "@angular/core";
import {ApiConst} from "../constants/ApiConst";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  public saveToLocalStorage(key: string, token: string): void {
    if(typeof window === 'undefined') { return;}
    window.localStorage.setItem(key, token);
  }

  public getFromLocalStorage(key: string): any {
    console.log(typeof window)
    if(typeof window === 'undefined') { return;}
    const token = window.localStorage.getItem(key);
    console.log(window.localStorage.getItem('token'))
    console.log(token)
    return token ? JSON.stringify(token) : null;
  }

  /**
   * Removes all attendance items from localstorage
   */
  public clearLocalStorage() {
    if(typeof window === 'undefined') { return;}
    window.localStorage.removeItem(ApiConst.TOKEN);
  }
}
