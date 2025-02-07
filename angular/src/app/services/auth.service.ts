import { computed, inject, Injectable, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { UserInterface } from '../model/user.interface';
import { SecurityService } from '../Security/security.service';
import { ApiConst } from '../constants/ApiConst';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private securityService = inject(SecurityService);

  public currentUserRole = computed(() => this.currentUser().role);
  public isLoggedIn = signal(false);
  public token = computed(() => this.currentUser().token);
  public isAdmin = computed(() => this.currentUserRole() === 'super_admin');
  public isArtistManager = computed(
    () => this.currentUserRole() === 'artist_manager'
  );
  public isArtist = computed(() => this.currentUserRole() === 'artist');

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
    address: '',
  });

  init() {
    const localData = this.securityService.getFromLocalStorage(ApiConst.USER);
    if (!localData) {
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
