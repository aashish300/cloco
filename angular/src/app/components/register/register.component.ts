import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    MatSelectModule,
    MatDatepickerModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  protected presentDate = new Date();

}
