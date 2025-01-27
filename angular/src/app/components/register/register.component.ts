import {Component, inject, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {CalendarModule} from 'primeng/calendar';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";
import {HttpClient} from "@angular/common/http";
import {ApiConst} from "../../constants/ApiConst";

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
  private http = inject(HttpClient);
  public genders = [
    {label: 'Male', value: 'M'},
    {label: 'Female', value: 'F'},
    {label: 'Other', value: 'O'}
  ];

  constructor(
  ) {
    this.registerForm = this.fb.group({
      firstName: ['test',[Validators.required]],
      lastName: ['test',[Validators.required]],
      email: ['test@gmail.com',[Validators.required]],
      password: ['123',[Validators.required]],
      dateOfBirth: ['2000-01-01',[Validators.required]],
      gender: ['',[Validators.required]],
      phoneNumber: ['9876543210',[Validators.required]],
      address: ['gwarko',[Validators.required]]
    })
  }

  ngOnInit() {

  }

  public addUser() {
    const formValue = this.registerForm.getRawValue();
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.REGISTER}`, formValue)
      .subscribe({
        next: res => {
          console.log(res);
        },
        error: err => {
          console.log(err)
        }
      })
  }
}
