import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
//import { LoginResponse } from '../_models/user';
import { Router } from '@angular/router';
import { LoginResponse } from '../models/login-response'


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  basePath="http://localhost:9010"
  constructor(private router: Router,
    private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  };

  // Handle API errors
  handleError(error: HttpErrorResponse) {
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
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

  loginForm(data): Observable<any> {
    return this.http
      .post<LoginResponse>(this.basePath + '/authenticate', data, this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

   // After login save token and other values(if any) in localStorage
   setUser(resp: LoginResponse) {
    let getToken="Bearer "+resp.token;
    console.log(resp);
    localStorage.setItem('token', getToken);
    this.router.navigate(['/welcome']);
  }

   // Checking if token is set
   isLoggedIn() {
    return localStorage.getItem('token') != null;
  }

   // After clearing localStorage redirect to login screen
   logout() {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }

  //  // Get data from server for Dashboard
  //  getData(data): Observable<LoginResponse> {
  //   return this.http
  //     .post<LoginResponse>(this.basePath + 'api.php', data, this.httpOptions)
  //     .pipe(
  //       retry(2),
  //       catchError(this.handleError)
  //     );
  // }   
}


