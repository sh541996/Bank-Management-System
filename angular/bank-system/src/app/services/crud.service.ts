import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Registeration } from '../models/registeration';
import { Account } from '../models/account';
import { stringify } from '@angular/compiler/src/util';
import { Investment } from '../models/investment';


@Injectable({
  providedIn: 'root'
})
export class CrudService {

  baseUrl = "http://localhost:9010";

  constructor(private router: Router,
    private http: HttpClient) { }

  // registeration
  register(register: Registeration): Observable<any> {

    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    return this.http.post(this.baseUrl + "/user/register", register, httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));
  }

  // welcome
  welcome() {

    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: localStorage.getItem('token'),
        responseType: 'application/json'
      })
    };

    return this.http.get<any>(this.baseUrl + "/user/welcome/name/" + localStorage.getItem('pan'), httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));
  }

  //accountRegister
  accountRegister(account: Account, contact: string) {

    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token'),
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.baseUrl + "/account/save/" + contact, account, httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));

  }

  //get list of account
  listAccount(): Observable<Account[]> {

    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: localStorage.getItem('token'),
        responseType: 'application/json'
      })
    };

    return this.http.get<Account[]>(this.baseUrl + "/account/" + localStorage.getItem('pan'), httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));

  }

  // investment
  investment(invest: Investment) {

    let httpOptions = {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token'),
        'Content-Type': 'application/json',
      })
    };

    return this.http.post(this.baseUrl + "/investment/invest", invest, httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));


  }

  //list of investment
  listInvestment(): Observable<Investment[]>{

    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: localStorage.getItem('token'),
        responseType: 'application/json'
      })
    };

    return this.http.get<any>(this.baseUrl + "/investment/summary/" + localStorage.getItem('pan'), httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));

  }

  //unique investment using fund id
  uniqueInvestment(fundId:number) {

    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: localStorage.getItem('token'),
        responseType: 'application/json'
      })
    };

    return this.http.get<any>(this.baseUrl + "/investment/summary/" + localStorage.getItem('pan') + "/" + fundId,
      httpOptions)
      .pipe(retry(2), catchError(this.errorHandler));

  }

  //error handler
  errorHandler(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.message}`);
    }

    localStorage.setItem('status', error.status.toString());
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

}

