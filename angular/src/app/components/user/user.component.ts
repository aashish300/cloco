import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {TableModule} from 'primeng/table';
import {Button} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {HttpClient} from "@angular/common/http";
import {ApiConst} from "../../constants/ApiConst";
import {InputTextModule} from "primeng/inputtext";
import {RadioButtonModule} from "primeng/radiobutton";
import {RoleConstant} from "../../constants/role.constant";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {PasswordModule} from "primeng/password";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Gender} from "../../utils/globa.utils";
import {UserInterface} from "../../model/user.interface";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    TableModule,
    Button,
    DialogModule,
    InputTextModule,
    RadioButtonModule,
    CalendarModule,
    DropdownModule,
    PasswordModule,
    ReactiveFormsModule
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})

export class UserComponent implements OnInit {

  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  public userForm: FormGroup;

  userList: WritableSignal<any> = signal('');
  protected readonly RoleConstant = RoleConstant;
  protected readonly Gender = Gender;
  visible = false;

  constructor() {
    this.userForm = this.fb.group({
      firstName: '',
      lastName: '',
      email: '',
      gender: '',
      phone: '',
      dob: '',
      address: '',
      role: ''
    })
  }

  ngOnInit() {
    this.fetchUser();
  }



  fetchUser() {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.FIND_ALL}`)
      .subscribe({
        next: (res) => {
          this.userList.set(res);
          console.log(res)
        }
      })
  }

  addUser() {
    this.visible = true;
  }

  editUser(user: any) {
    this.visible = true;
    this.userForm.patchValue(user)
  }

  saveUser() {
    this.http.put(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.UPDATE}`, this.userForm.getRawValue())
      .subscribe({
        next: (res) => {
          console.log(res)
        }
      })
  }

  deleteUser(user: UserInterface) {
    this.http.delete(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.DELETE}/${user.id}`)
      .subscribe({
        next: () => {

        }
      })
  }
}
