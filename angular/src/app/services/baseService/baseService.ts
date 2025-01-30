import {ApiConst} from "../../constants/ApiConst";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {Injectable} from "@angular/core";


@Injectable({
  providedIn: "root"
})
export class BaseService {
  private baseUrl: string = ApiConst.SERVER_URL;

  constructor(private http: HttpClient) { }


  public postRequest<T>(url: string, data: any, message: string) {
    return this.http.post<T>(this.baseUrl + url, data).pipe(
      catchError(this.handleError<T>(null, message))
    );
  }

  private handleError<T>(result?: T, message?: string) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
