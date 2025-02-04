import {inject, Injectable, PLATFORM_ID} from "@angular/core";
import {ApiConst} from "../constants/ApiConst";
import {UserInterface} from "../model/user.interface";
import {DOCUMENT, isPlatformBrowser} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private document = inject(DOCUMENT);
  private platformId = inject(PLATFORM_ID);


  public saveToLocalStorage(key: string, value: string | UserInterface): void {
    if(isPlatformBrowser(this.platformId)) {
      window.localStorage.setItem(key, JSON.stringify(value as any));

    }
  }

  public getFromLocalStorage(key: string): any {
    if(!isPlatformBrowser(this.platformId)) {
      console.log('Attempted to access localStorage on the server')
      return null;
    }
    const token = window.localStorage?.getItem(key);
    return token ? JSON.parse(token) : null;

  }

  /**
   * Removes all attendance items from localstorage
   */
  public clearLocalStorage() {
    window.localStorage.removeItem(ApiConst.USER);
  }
}
