import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DialogModule} from "primeng/dialog";
import {DropdownModule} from "primeng/dropdown";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {PrimeTemplate} from "primeng/api";
import {HttpClient} from "@angular/common/http";
import {ApiConst} from "../../constants/ApiConst";
import {ArtistInterface} from "../../model/artist.interface";
import {Gender} from "../../utils/globa.utils";

@Component({
  selector: 'app-artist',
  standalone: true,
  imports: [
    Button,
    CalendarModule,
    DialogModule,
    DropdownModule,
    FormsModule,
    InputTextModule,
    PrimeTemplate,
    ReactiveFormsModule
  ],
  templateUrl: './artist.component.html',
  styleUrl: './artist.component.scss'
})
export class ArtistComponent implements OnInit {

  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  protected readonly Gender = Gender;

  public artistForm: FormGroup;

  public visible = false;
  public artistList: WritableSignal<ArtistInterface[]> = signal([] as ArtistInterface[]);

  constructor() {
    this.artistForm = this.fb.group({
      name: '',
      dob: '',
      gender: '',
      address: '',
      first_release_year: '',
      no_of_albums_released: '',
    })
  }

  ngOnInit() {
    this.fetchArtist();
  }

  fetchArtist() {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.FIND_ALL}`)
      .subscribe({
        next: (res: any) => {
          this.artistList.set(res);
        }
      })
  }

  openDialog() {
    this.visible = true;
  }

  editArtist(artist: ArtistInterface) {
    this.visible = true;
    this.artistForm.patchValue(artist);
  }

  deleteArtist(artist: any) {

  }

  addArtist() {
  }

}
