import {Component, inject, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {CalendarModule} from 'primeng/calendar';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";

@Component({
  selector: 'app-register',
  standalone: true,
  providers: [
  ],
  imports: [
    RouterLink,
    CalendarModule,
    ReactiveFormsModule,
    DropdownModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit{

  protected presentDate = new Date();
  public registerForm: FormGroup;
  protected fb = inject(FormBuilder);
  public genders = [
    {label: 'Male', value: 'M'},
    {label: 'Female', value: 'F'},
    {label: 'Other', value: 'O'}
  ];

  constructor(
  ) {
    this.registerForm = this.fb.group({
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      dateOfBirth: '',
      gender: '',
      phoneNumber: '',
      address: ''
    })
  }

  ngOnInit() {

  }
}
