import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ApiConst} from "../../constants/ApiConst";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "primeng/api";
import { ToastModule } from 'primeng/toast';
import {SecurityService} from "../../Security/security.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule,
    ToastModule,
  ],
  providers:[
    MessageService
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);
  private messageService = inject(MessageService);


  ngOnInit() {
    SecurityService.clearLocalStorage();
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    })
  }

  public login() {
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.LOGIN}`, this.loginForm.getRawValue())
      .subscribe({
        next: (res: any) => {
          if(!res) return;
          this.messageService.add({severity: 'success', summary: 'Success', detail: res?.message});
          SecurityService.saveToLocalStorage(ApiConst.TOKEN, res?.user?.token);
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error(err);
          this.messageService.add({severity: 'error', summary: 'Error', detail: err?.error.message});
        }
      })
  }
}
