import {Component, inject} from '@angular/core';
import {RouterLink} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ApiConst} from "../../constants/ApiConst";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  public loginForm: FormGroup;
  private http = inject(HttpClient);

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    })
  }

  public submit() {
    console.log('here');
    console.log(this.loginForm.getRawValue());
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.LOGIN}`, this.loginForm.getRawValue())
      .subscribe({
        next: (res) => {},
        error: err => {}
      })
  }
}
