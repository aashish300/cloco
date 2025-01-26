import {ApiConst} from "../../constants/ApiConst";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs";
import {inject} from "@angular/core";


@Injectable({
  ProvidedIn: 'root'
})
export class BaseService {
  private baseUrl: string = ApiConst.SERVER_URL;

  constructor(private http: HttpClient) { }


  public postRequest(url: string, data: any, message) {
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
