import {Component, inject, OnInit} from '@angular/core';
import {MenubarModule} from "primeng/menubar";
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";
import {SecurityService} from "../../../Security/security.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MenubarModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent{

  public router = inject(Router);

  logout() {
    this.router.navigate(['/']);
    SecurityService.clearLocalStorage();
  }

}
