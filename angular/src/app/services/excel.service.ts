import {Injectable} from "@angular/core";
import * as XLSX from 'xlsx';
import {WorkBook} from 'xlsx';
import {Observable, Subscriber} from 'rxjs';

Injectable({ providedIn: "root" });
export class ExcelService {

  workbook: any = {};

  readExcel(excelFile: Blob): Observable<any> {
    const readFile = new FileReader();
    readFile.readAsArrayBuffer(excelFile);
    return new Observable((observer: Subscriber<any[]>): void => {
      readFile.onload = (e) => {
        const fileResult: any = readFile.result;
        const data = new Uint8Array(fileResult);
        const arr = [];
        for (let i = 0; i !== data.length; ++i) {
          arr[i] = String.fromCharCode(data[i]);
        }
        this.workbook = XLSX.read(arr.join(''), { type: 'binary' });
        observer.next(this.workbook);
        observer.complete();
      };
      readFile.onerror = (error: any): void => {
        observer.error(error);
      };
    });
  }

  // getSheetAsJson(wb: WorkBook, index: number) {
  //   // Use filter to return only those rows where at least one value is not an empty string or null/undefined
  //   return XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[index]], { raw: false })
  //     // trim all the header name
  //     .map(row => Object.fromEntries(Object.entries(row).map(([key, value]) => [key.trim(), value])))
  //     .filter(row=> Object.values(row).some(value=>typeof value === 'string'?value.trim()!="":!!value));
  // }
}
