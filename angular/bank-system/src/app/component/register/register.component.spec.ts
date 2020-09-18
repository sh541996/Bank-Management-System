import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Registeration } from 'src/app/models/registeration';
import { Observable, Observer, throwError } from 'rxjs';

let mockRegisteration = new Registeration("shubham", "kumar", "aabbccddee", "983536", "9852625560", "sh541996@gmail.com");

fdescribe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
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
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    crudService = TestBed.get(CrudService); 
    fixture.detectChanges();
  });

  /////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /////////////////////////////
  it('onSubmit() method when registeration is successful', fakeAsync(()=>{
    component.registeration=mockRegisteration;
    spyOn(crudService, 'register').withArgs(mockRegisteration).and.returnValue( Observable.create((observer: Observer<any>) => {
      observer.next({name: "shubham kumar"});
    }));

    component.onSubmit()
    fixture.detectChanges();
    tick;

    expect(component.successMsg).toEqual("shubham kumar your registeration is successful");
  }));;

   /////////////////////////////
   it('onSubmit() method when registeration is unsuccessful', fakeAsync(()=>{
    component.registeration=mockRegisteration;
    spyOn(crudService, 'register').withArgs(mockRegisteration).and.returnValue(throwError(( Observable.create((observer: Observer<any>) => {
      observer.next({error: "registeration is unsuccessful"});
    }))));

    component.onSubmit()
    fixture.detectChanges();
    tick;

    expect(component.errorMsg).toEqual("user is already exist");
  }));;

  ////////////////////////////////
  it('validateDob() method when age is less than 18yrs', fakeAsync(()=>{

    let dob="2010/09/28";
    component.validateDob(dob);
    fixture.detectChanges();
    tick;
    expect(component.age).toEqual(false);
  }))

   ////////////////////////////////
   it('validateDob() method when age is more than 18yrs', fakeAsync(()=>{

    let dob = new Date("1990/08/23");
    component.validateDob(dob);
    tick();
    fixture.detectChanges();
    expect(component.age).toEqual(true);
    
  }))

});
