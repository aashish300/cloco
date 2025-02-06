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
import {ArtistInterface} from "../../model/artist.interface";

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
  public artistDetails!: ArtistInterface;

  constructor() {
    this.musicForm = this.fb.group({
      id: '',
      title: '',
      albumName: '',
      genre: ''
    })
  }


  ngOnInit() {
    this.fetchArtistById(this.route.snapshot.params['id']);
  }

  public fetchArtistById(id: number) {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${id}`)
      .subscribe({
        next: (res: any) => {
          this.artistDetails = res?.data;
          this.fetchSong(this.route.snapshot.params['id']);
        }
      })
  }

  public fetchSong(id: number) {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${ApiConst.FIND_MUSIC_BY_ARTIST_ID}/${id}`)
      .subscribe({
        next: (res: any) => {
          if(!res) return;
          this.songList.set(res?.data);
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
    const payload = {
      ...this.musicForm.getRawValue(),
      artist: this.artistDetails
    }
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${ApiConst.SAVE}`, payload)
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(this.artistDetails.id);
        }
      })
  }

  updateMusic() {
    const payload = {
      ...this.musicForm.getRawValue(),
      artist: this.artistDetails
    }
    this.http.put(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${ApiConst.UPDATE}`, payload)
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(this.artistDetails.id);
        }
      })
  }

  deleteMusic(music: MusicInterface) {
    this.http.delete(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.MUSIC}/${music.id}`)
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchSong(this.artistDetails.id);
        }
      })
  }

}
