import {inject, Injectable} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AuthService {

  private router = inject(Router);
  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
}
