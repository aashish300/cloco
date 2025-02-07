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
import {WorkBook} from "xlsx";
import {Router} from "@angular/router";

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
  private router: Router = inject(Router);
  public pagination = {
    size: 2,
    page: 1,
    totalPagesList: [] as number[],
    totalRow: 0
  }

  protected readonly genders = Gender;
  public isUpdate = false;
  workbook: WorkBook | undefined;

  public artistForm: FormGroup;

  public visible = false;
  public artistList: WritableSignal<ArtistInterface[]> = signal([] as ArtistInterface[]);
  public inputFile: any;

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
    const { page, size } = this.pagination;
    this.http.get(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.FIND_ALL_BY_PAGINATION}?page=${page}&size=${size}`)
      .subscribe({
        next: (res: any) => {
          this.artistList.set(res?.list);
          const pages = [];
          const page = Math.ceil(res?.totalCount / this.pagination.size);
          for(let i=1; i<= page; i++) {
            pages.push(i);
          }
          this.pagination.totalRow = res?.totalCount
          this.pagination.totalPagesList = pages;

          this.artistForm.reset();
          this.isUpdate = false;
          this.visible = false;
        }
      })
  }

  goToPage(page: number) {
    this.pagination.page = page;
    this.fetchArtist();
  }

  importCSV(event: any) {
    const formData = new FormData();
    formData.append('file', event.target.files[0]);

    this.http.post(`${ApiConst.SERVER_URL}/${ApiConst.API}/${ApiConst.ARTIST}/${ApiConst.CSV}/${ApiConst.UPLOAD}`, formData, {
      // headers: {
      //   'Content-Type': 'multipart/form-data'
      // }
    })
      .subscribe({
        next: (res: any) => {

        }
      })
    // this.excelService.readExcel(event.target.files[0])
    //   .subscribe({
    //     next: (wb: WorkBook) => {
    //       // workbook object containing routine workbook
    //       this.workbook = wb;
    //       // parse the excel wb json
    //       const excelJson = this.excelService.getSheetAsJson(wb, 0);
    //       console.log(excelJson);
    //     },
    //     error: () => {
    //       console.log('ERROR');
    //     }});
    this.inputFile = null;
  }

  public exportCSV() {

  }


  openDialog(selectedArtist: ArtistInterface | null = null) {
    this.visible = true;
    if(selectedArtist) {
      this.isUpdate = true;
      this.artistForm.patchValue({...selectedArtist, dob: new Date(selectedArtist.dob)});
    }else {
      this.isUpdate = false;
      this.artistForm.reset();
    }
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

  viewArtist(artist: ArtistInterface) {
    this.router.navigate(['/artist', artist.id]);
  }


}
