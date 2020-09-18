import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { RegisterAccountComponent } from './register-account.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Account } from 'src/app/models/account';
import { throwError, Observable, Observer } from 'rxjs';

let mockAccount = new Account("1234567890", "aabbcc", "sbi", "aa11bb22", "abcdefgh");

fdescribe('RegisterAccountComponent', () => {
  let component: RegisterAccountComponent;
  let fixture: ComponentFixture<RegisterAccountComponent>;
  let crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterAccountComponent ],
      providers: [CrudService],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule.withRoutes([]),
        FormsModule,
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterAccountComponent);
    component = fixture.componentInstance;
    crudService = TestBed.get(CrudService);
    component.account=mockAccount;
    component.contact="9835363424"
    fixture.detectChanges();
  });

  //////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //////////////////////////////
  it("onSubmit() method when account is registered successfully", fakeAsync(()=>{

    spyOn(crudService, 'accountRegister').and.returnValue(
      ( Observable.create((observer: Observer<any>) => {
      observer.next(mockAccount);
    })));

    component.onSubmit();
    tick();
    fixture.detectChanges();

    expect(component.response).toEqual(mockAccount);
    expect(component.flag).toEqual(false);
    expect(crudService.accountRegister).toHaveBeenCalled();
  }));

  /////////////////////////////////////
  it("onSubmit() method when account is not regiter as already 4 account is registerd", fakeAsync(()=>{

    spyOn(crudService, 'accountRegister').and.returnValue(throwError(( Observable.create((observer: Observer<any>) => {
    observer.next({error: "already 4 account is registerd"});
    }))));
    localStorage.setItem('status','400');

    component.onSubmit();
    tick();
    fixture.detectChanges();

    expect(component.errorMsg).toEqual("You cant register more than 4 bank account");
    expect(crudService.accountRegister).toHaveBeenCalled();
    
  }));


});
