import {Component, inject, OnInit, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ToastModule} from "primeng/toast";
import {MessageService, PrimeNGConfig} from "primeng/api";
import {NavbarComponent} from "./components/common/navbar/navbar.component";
import {SecurityService} from "./Security/security.service";
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ToastModule, NavbarComponent],
  providers: [MessageService, SecurityService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  public authService = inject(AuthService);
  private primengConfig = inject(PrimeNGConfig);

  ngOnInit() {
    this.primengConfig.ripple = true;

    this.primengConfig.zIndex = {
      modal: 1100,    // dialog, sidebar
      overlay: 1000,  // dropdown, overlaypanel
      menu: 1000,     // overlay menus
      tooltip: 1100   // tooltip
    };

    this.primengConfig.csp.set({ nonce: '...'});
  }
}
