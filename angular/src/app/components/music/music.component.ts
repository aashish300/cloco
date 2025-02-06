import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApiConst} from "../../constants/ApiConst";
import {ActivatedRoute, Router} from "@angular/router";
import {CalendarModule} from "primeng/calendar";
import {DialogModule} from "primeng/dialog";
import {DropdownModule} from "primeng/dropdown";
import {PrimeTemplate} from "primeng/api";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MusicInterface} from "../../model/music.interface";

@Component({
  selector: 'app-music',
  standalone: true,
  imports: [
    CalendarModule,
    DialogModule,
    DropdownModule,
    PrimeTemplate,
    ReactiveFormsModule
  ],
  templateUrl: './music.component.html',
  styleUrl: './music.component.scss'
})
export class MusicComponent implements OnInit {

  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private fb: FormBuilder = inject(FormBuilder);

  public visible = false;
  public songList: WritableSignal<MusicInterface[]> = signal([] as MusicInterface[]);
  public musicForm: FormGroup;
  public isUpdate = false;

  constructor() {
    this.musicForm = this.fb.group({
      id: '',
      title: '',
      albums_name: '',
      genre: ''
    })
  }


  ngOnInit() {
    this.fetchSong(this.route.snapshot.params['id']);
  }

  public fetchSong(id: number) {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${id}`)
      .subscribe({
        next: (res: any) => {
          if(!res) return;
          this.songList.set(res?.music);
          this.visible = false;
          this.musicForm.reset();
        }
      })
  }

  openDialog(selectedMusic: MusicInterface | null = null) {
    this.visible = true;
    if(selectedMusic) {
      this.isUpdate = true;
      this.musicForm.patchValue({...selectedMusic});
    }else {
      this.isUpdate = false;
      this.musicForm.reset();
    }
  }

  addMusic() {
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${ApiConst.SAVE}`, this.musicForm.getRawValue())
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(1);
        }
      })
  }

  updateMusic() {
    this.http.put(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${ApiConst.UPDATE}`, this.musicForm.getRawValue())
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(1);
        }
      })
  }

  deleteMusic(music: MusicInterface) {
    this.http.delete(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${music.id}`, this.musicForm.getRawValue())
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(1);
        }
      })
  }

}
