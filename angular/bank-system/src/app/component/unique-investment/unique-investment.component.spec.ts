import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { UniqueInvestmentComponent } from './unique-investment.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Investment } from 'src/app/models/investment';
import { Observable, Observer, throwError } from 'rxjs';


let mockInvestment = new Investment("shubham", "1234567890", '89', "aabbcc");

fdescribe('UniqueInvestmentComponent', () => {
  let component: UniqueInvestmentComponent;
  let fixture: ComponentFixture<UniqueInvestmentComponent>;
  let crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniqueInvestmentComponent ],
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
    fixture = TestBed.createComponent(UniqueInvestmentComponent);
    component = fixture.componentInstance;
    crudService = TestBed.get(CrudService);
    component.fundId = 12345;
    localStorage.setItem('status','500')
    fixture.detectChanges();
  });

  //////////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /////////////////////////////////
  it('onSubmit() method when we get investment data successfully', fakeAsync(()=>{

    spyOn(crudService, 'uniqueInvestment').and.returnValue(
      ( Observable.create((observer: Observer<any>) => {
      observer.next(mockInvestment);
    })));

    component.onSubmit();
    tick();
    fixture.detectChanges();

    expect(crudService.uniqueInvestment).toHaveBeenCalled()
    expect(component.response).toEqual(mockInvestment);
    expect(component.flag).toEqual(true);

  }));

  ////////////////////////////////////
  it('onSubmit() method when we not get investment data successfully', fakeAsync(()=>{

    spyOn(crudService, 'uniqueInvestment').and.returnValue(throwError(
      ( Observable.create((observer: Observer<any>) => {
      observer.next({error:"You provide either wrong fund-id or you not made any investment"});
    }))));

    component.onSubmit();
    tick();
    fixture.detectChanges();

    expect(crudService.uniqueInvestment).toHaveBeenCalled()
    expect(component.errorMsg).toEqual("You provide either wrong fund-id or you not made any investment");

  }));
});
