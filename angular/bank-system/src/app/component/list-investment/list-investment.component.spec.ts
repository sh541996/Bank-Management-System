import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { ListInvestmentComponent } from './list-investment.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Investment } from 'src/app/models/investment';
import { Observable, Observer } from 'rxjs';



let mockInvestmentList1 = new Investment("shubham", "1234567890", '89', "aabbcc");
let mockInvestmentList2 = new Investment("ravi", "0987654321", '80', "aabbcc");
let mockInvestment=[mockInvestmentList1, mockInvestmentList2];

class MockCrudService {
  listInvestment() {
    return Observable.create((observer: Observer<Investment[]>) => {
      observer.next(mockInvestment);
    });
  }
}


let mockCrudService: MockCrudService;

fdescribe('ListInvestmentComponent', () => {
  let component: ListInvestmentComponent;
  let fixture: ComponentFixture<ListInvestmentComponent>;
  let  crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListInvestmentComponent ],
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
    fixture = TestBed.createComponent(ListInvestmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    crudService=TestBed.get(CrudService);
   // mockCrudService=new MockCrudService();
  });

  ////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  ////////////////////////////
  it('ngOnInit() method when listInvestment() return a list and else block is true', fakeAsync(()=>{
   
    spyOn(crudService, 'listInvestment').and.returnValue( Observable.create((observer: Observer<Investment[]>) => {
      observer.next(mockInvestment);
    }));

    component.ngOnInit();  
    fixture.detectChanges();
    tick;
    expect(component.investments).toEqual(mockInvestment);
    expect(component.flag2).toEqual(false);
    expect(component.flag1).toEqual(true);
    expect(crudService.listInvestment).toHaveBeenCalled();
  }));

  ////////////////////////////
  it('ngOnInit() method when listInvestment() return a null list and if block is true', fakeAsync(()=>{

    
    spyOn(crudService, 'listInvestment').and.returnValue( Observable.create((observer: Observer<Investment[]>) => {
      observer.next([]);
    }));

    component.ngOnInit();  
    fixture.detectChanges();
    tick;
    expect(component.investments).toEqual([]);
    expect(component.flag2).toEqual(true);
    expect(component.flag1).toEqual(false);
    expect(crudService.listInvestment).toHaveBeenCalled();
  }));


});

