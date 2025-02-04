import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {TableModule} from 'primeng/table';
import {Button} from "primeng/button";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    TableModule,
    Button
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})

export class UserComponent implements OnInit {

  userList: WritableSignal<any> = signal('');

  ngOnInit() {
  }

  addUser() {

  }

}
