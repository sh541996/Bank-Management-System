import { async, ComponentFixture, TestBed, fakeAsync, tick, } from '@angular/core/testing';
import { InvestComponent } from './invest.component';
import { CrudService } from 'src/app/services/crud.service';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { Observable, Observer} from 'rxjs';
import { Account } from 'src/app/models/account';
import { Investment } from 'src/app/models/investment';


let mockInvestment =new Investment("shubham","1234567890","23","aabbccdd");
let mockAccount = [new Account("1234567890", "aabbcc", "sbi", "aa11bb22", "abcdefgh")];


fdescribe('InvestComponent', () => {
  let component: InvestComponent;
  let fixture: ComponentFixture<InvestComponent>;
  let crudService: CrudService;



  /////////////
  beforeEach(async(() => {

    TestBed.configureTestingModule({
      declarations: [InvestComponent],
      providers: [CrudService],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule.withRoutes([]),
        FormsModule,
      ],

    })
      .compileComponents();
  }));

  ///////////////
  beforeEach(() => {
    fixture = TestBed.createComponent(InvestComponent);
    component = fixture.componentInstance;
    crudService = TestBed.get(CrudService);
    fixture.detectChanges();
  });

  /////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  ////////////////////
  it("ngOnit() method when listAccount() return a list of account", fakeAsync(()=>{

    spyOn(crudService, 'listAccount').and.returnValue( Observable.create((observer: Observer<Account[]>) => {
      observer.next(mockAccount);
    }));

    component.ngOnInit();
    tick();
    fixture.detectChanges();

    expect(crudService.listAccount).toHaveBeenCalled();
    expect(component.listAccount).toEqual(mockAccount);
    
  }));

  ////////////////////////////
  it("ngOnit() method when listAccount() return no account", fakeAsync(()=>{

    spyOn(crudService, 'listAccount').and.returnValue( Observable.create((observer: Observer<Account[]>) => {
      observer.next([]);
    }));

    component.ngOnInit();
    tick();
    fixture.detectChanges();

    expect(crudService.listAccount).toHaveBeenCalled();
    expect(component.listAccount).toEqual([]);
    expect(component.errorMsg).toEqual("You not register any bank account");

  }));

  ///////////////////////////////////
  it('onSubmit() method', fakeAsync(()=>{

    let data={response:mockInvestment};
    component.investment=mockInvestment;
    spyOn(crudService, 'investment').and.returnValue( Observable.create((observer: Observer<any>) => {
      observer.next(mockInvestment);
    }));

    component.onSubmit();
    tick();
    fixture.detectChanges();

    expect(crudService.investment).toHaveBeenCalled();
    expect(component.response).toEqual(mockInvestment);
    expect(component.flag).toEqual(false);
  }))
});

