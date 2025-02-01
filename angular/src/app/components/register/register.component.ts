import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {CalendarModule} from 'primeng/calendar';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";
import {HttpClient} from "@angular/common/http";
import {ApiConst} from "../../constants/ApiConst";
import {RoleConstant} from "../../constants/role.constant";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-register',
  standalone: true,
  providers: [
    MessageService
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
  protected fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private messageService = inject(MessageService);
  private router = inject(Router);
  public registerForm: FormGroup;
  public genders = [
    {label: 'Male', value: 'M'},
    {label: 'Female', value: 'F'},
    {label: 'Other', value: 'O'}
  ];
  protected readonly RoleConstant = RoleConstant;

  constructor(
  ) {
    this.registerForm = this.fb.group({
      firstName: ['',[Validators.required]],
      lastName: ['',[Validators.required]],
      email: ['',[Validators.required]],
      password: ['',[Validators.required]],
      dob: ['',[Validators.required]],
      gender: ['',[Validators.required]],
      phone: ['',[Validators.required]],
      address: ['',[Validators.required]],
      role: ['', Validators.required]
    })
  }

  ngOnInit() {

  }

  public addUser() {
    const formValue = this.registerForm.getRawValue();
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.REGISTER}`, formValue)
      .subscribe({
        next: (res: any) => {
          console.log(res)
          if(!res) return;
          this.messageService.add({severity: 'success', summary: 'Success', detail: res?.message });
          this.registerForm.reset();
          this.router.navigate(['/login']);
        },
        error: err => {
          console.log(err);
        }
      })
  }

}
