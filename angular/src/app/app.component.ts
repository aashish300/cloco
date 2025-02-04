import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {NavbarComponent} from "./components/common/navbar/navbar.component";
import {SecurityService} from "./Security/security.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ToastModule, NavbarComponent],
  providers: [MessageService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  public isLoggedIn: boolean = false;

  ngOnInit() {
    this.isLoggedIn = !!SecurityService.getFromLocalStorage('key');
  }

}
