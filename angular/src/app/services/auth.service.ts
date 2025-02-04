import {computed, inject, Injectable, signal} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserInterface} from "../model/user.interface";
import {SecurityService} from "../Security/security.service";
import {ApiConst} from "../constants/ApiConst";

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private router = inject(Router);
  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private securityService = inject(SecurityService);
  public currentUser = signal({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phone: 0,
    token: '',
    role: '',
    gender: '',
    dob: new Date(),
    address: ''
  });

  public currentUserRole = computed(() => this.currentUser().role);
  public isLoggedIn = signal(false);
  public token = computed(() => this.currentUser().token);

  init() {
    const localData = this.securityService.getFromLocalStorage(ApiConst.USER);
    if(!localData) {
      this.isLoggedIn.set(false);
       return;
    }
    this.isLoggedIn.set(true);
    this.currentUser.set(localData);
  }

  saveUser(user: UserInterface) {

    this.currentUser.set(user);
    this.isLoggedIn.set(true);

    this.securityService.saveToLocalStorage(ApiConst.USER, user);
  }

  logout() {
    this.securityService.clearLocalStorage();
  }

}
