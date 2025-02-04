import {Component, inject, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {NavbarComponent} from "./components/common/navbar/navbar.component";
import {SecurityService} from "./Security/security.service";
import { initFlowbite } from 'flowbite';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ToastModule, NavbarComponent],
  providers: [MessageService, SecurityService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  public isLoggedIn: boolean = false;
  private securityService = inject(SecurityService);

  ngOnInit() {
    this.isLoggedIn = !!this.securityService.getFromLocalStorage('token');
  }

}
