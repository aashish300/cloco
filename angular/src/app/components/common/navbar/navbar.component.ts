import { Component, inject } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { Router } from '@angular/router';
import { SecurityService } from '../../../Security/security.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MenubarModule],
  providers: [SecurityService],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  public router = inject(Router);
  public authService = inject(AuthService);

  logout() {
    this.authService.logout();
  }
}
