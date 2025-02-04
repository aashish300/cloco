import {Component, inject, OnInit} from '@angular/core';
import {NavbarComponent} from "../common/navbar/navbar.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  standalone: true,
    imports: [
        NavbarComponent
    ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  private router = inject(Router);
  ngOnInit() {
    this.router.navigate(['/user'])
  }
}
