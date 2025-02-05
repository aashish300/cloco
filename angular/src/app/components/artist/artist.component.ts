import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
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
import {LoginComponent} from "../login/login.component";

@Component({
  selector: 'app-artist',
  standalone: true,
  imports: [
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
  protected readonly genders = Gender;
  public isUpdate = false;

  public artistForm: FormGroup;

  public visible = false;
  public artistList: WritableSignal<ArtistInterface[]> = signal([] as ArtistInterface[]);

  constructor() {
    this.artistForm = this.fb.group({
      id:'',
      name: '',
      dob: '',
      gender: '',
      address: '',
      firstReleaseYear: '',
      noOfAlbumsReleased: '',
    })
  }

  ngOnInit() {
    this.fetchArtist();
  }

  fetchArtist() {
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.FIND_ALL}`)
      .subscribe({
        next: (res: any) => {
          this.artistList.set(res?.list);
          this.artistForm.reset();
          this.isUpdate = false;
          this.visible = false;
        }
      })
  }

  openDialog(selectedArtist: ArtistInterface | null = null) {
    this.visible = true;
    if(selectedArtist) {
      this.isUpdate = true;
      this.artistForm.patchValue({...selectedArtist, dob: new Date(selectedArtist.dob)});
    }
    selectedArtist ? this.isUpdate = true : null;
  }

  addArtist() {
    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.SAVE}`, this.artistForm.getRawValue())
      .subscribe({
        next: (res) => {
          console.log(res)
          if(!res) return;
          this.fetchArtist();
        }
      })
  }

  updateArtist() {
    this.http.put(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.UPDATE}`, this.artistForm.getRawValue())
      .subscribe({
        next: (res) => {
          console.log('res',res)
          if(!res) return;
          this.fetchArtist();
        }
      })
  }

  deleteArtist(artist: ArtistInterface) {
    this.http.delete(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${artist.id}`)
      .subscribe({
        next: (res) => {
          if(!res) return;
          this.fetchArtist();
        }
      })
  }


}
