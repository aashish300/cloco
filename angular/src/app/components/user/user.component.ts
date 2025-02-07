import {
  Component,
  inject,
  OnInit,
  signal,
  WritableSignal,
} from '@angular/core';
import { TableModule } from 'primeng/table';
import { Button } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { HttpClient } from '@angular/common/http';
import { ApiConst } from '../../constants/ApiConst';
import { InputTextModule } from 'primeng/inputtext';
import { RadioButtonModule } from 'primeng/radiobutton';
import { RoleConstant } from '../../constants/role.constant';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { PasswordModule } from 'primeng/password';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Gender } from '../../utils/globa.utils';
import { UserInterface } from '../../model/user.interface';

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
    ReactiveFormsModule,
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss',
})
export class UserComponent implements OnInit {
  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  public userForm: FormGroup;

  userList: WritableSignal<any> = signal('');
  protected readonly RoleConstant = RoleConstant;
  protected readonly genders = Gender;

  visible = false;
  public isUpdate = false;

  constructor() {
    this.userForm = this.fb.group({
      id: '',
      firstName: '',
      lastName: '',
      password: '',
      email: '',
      gender: '',
      phone: '',
      dob: '',
      address: '',
      role: '',
    });
  }

  ngOnInit() {
    this.fetchUser();
  }

  fetchUser() {
    this.http
      .get(
        `${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.FIND_ALL}`
      )
      .subscribe({
        next: (res: any) => {
          this.userList.set(res?.data);
          this.isUpdate = false;
          this.visible = false;
        },
      });
  }

  openDialog(selectedRow: any = null) {
    this.visible = true;
    this.userForm.reset();
    if (selectedRow) {
      this.userForm.patchValue({
        ...selectedRow,
        dob: new Date(selectedRow.dob),
      });
      this.isUpdate = true;
    } else {
      this.isUpdate = false;
    }
  }

  addUser() {
    this.http
      .post(
        `${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.SAVE}`,
        this.userForm.getRawValue()
      )
      .subscribe({
        next: (res) => {},
        complete: () => {
          this.isUpdate = false;
          this.fetchUser();
        },
      });
  }

  updateUser() {
    this.http
      .put(
        `${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${ApiConst.UPDATE}`,
        this.userForm.getRawValue()
      )
      .subscribe({
        next: (res) => {},
        complete: () => {
          this.fetchUser();
        },
      });
  }

  deleteUser(user: UserInterface) {
    this.http
      .delete(
        `${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.USER}/${user.id}`
      )
      .subscribe({
        next: () => {},
        complete: () => {
          this.fetchUser();
        },
      });
  }
}
