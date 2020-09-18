import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Observable, Observer } from 'rxjs';
import { Router } from '@angular/router';

let mockModel:any = {action: "login",pan: "HHBSR5420B", password: "shubham123"};
let mockResponse = {token:"aabbccddee"};

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let location: Location;
  let router: Router;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [AuthService, RouterTestingModule],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule.withRoutes([]),
        FormsModule,
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authService = TestBed.get(AuthService);
    router = TestBed.get(Router);
    router.initialNavigation();
    fixture.detectChanges();
  });

  ////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
    
  });

  ////////////////////////////
  // it("login()", fakeAsync(()=>{

  //   component.model={action: "login",pan: "HHBSR5420B", password: "shubham123"};
  //   spyOn(authService, 'loginForm').withArgs(mockModel).and.throwError( Observable.create((observer: Observer<any>) => {
  //     observer.next("error");
  //   }));

  //   component.login();
  //   fixture.detectChanges();
  //   tick;

  //   expect(component.model).toEqual(mockModel);
  //   expect(authService.loginForm).toHaveBeenCalled();

  // }))

 });
