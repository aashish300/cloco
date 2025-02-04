import {Injectable} from "@angular/core";
import {ApiConst} from "../constants/ApiConst";

@Injectable()
export class SecurityService {

  public static saveToLocalStorage(key: string, token: string): void {
    localStorage.setItem(key, token);
  }

  public static getFromLocalStorage(key: string): any {
    const token = localStorage.getItem(key);
    console.log(token)
    return token ? JSON.stringify(token) : null;
  }

  /**
   * Removes all attendance items from localstorage
   */
  public static clearLocalStorage() {
    localStorage.removeItem(ApiConst.TOKEN);
  }
}
