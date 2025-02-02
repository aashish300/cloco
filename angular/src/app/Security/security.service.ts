import {Injectable} from "@angular/core";
import {ApiConst} from "../constants/ApiConst";

@Injectable()
export class SecurityService {

  public static saveToLocalStorage(key: string, token: string): void {
    localStorage.setItem(key, token);
  }

  public static getFromLocalStorage(key: string): any {
    return JSON.parse(localStorage.getItem(key)!);
  }

  /**
   * Removes all attendance items from localstorage
   */
  public static clearLocalStorage() {
    localStorage.removeItem(ApiConst.TOKEN);
  }
}
