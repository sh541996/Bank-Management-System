import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { ListRegisterAccountComponent } from './list-register-account.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Account } from 'src/app/models/account';
import { Observable, Observer } from 'rxjs';


let account1 = new Account("1234567890", "abcde", "sbi", "a1a2", "abcdefgh");
let account2 = new Account("0987654321", "fghij", "axis", "b1b2", "ijklmnop");
let mockAccount=[account1, account2];

fdescribe('ListRegisterAccountComponent', () => {
  let component: ListRegisterAccountComponent;
  let fixture: ComponentFixture<ListRegisterAccountComponent>;
  let crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRegisterAccountComponent ],
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
    fixture = TestBed.createComponent(ListRegisterAccountComponent);
    component = fixture.componentInstance;
    crudService=TestBed.get(CrudService);
    fixture.detectChanges();
    
  });

  ///////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  ///////////////////////////////
  it("ngOnIt() method when listAccount() return list and else block true", fakeAsync(()=>{

    spyOn(crudService, 'listAccount').and.returnValue( Observable.create((observer: Observer<Account[]>) => {
      observer.next(mockAccount);
    }));

    component.ngOnInit();
    tick;

    expect(component.listAccount).toEqual(mockAccount);
    expect(crudService.listAccount).toHaveBeenCalled();
    expect(component.flag2).toEqual(false);
    expect(component.flag1).toEqual(true);
  }));

  /////////////////////////////////
  it("ngOnIt() method when listAccount() return null list and if block true", fakeAsync(()=>{

    spyOn(crudService, 'listAccount').and.returnValue( Observable.create((observer: Observer<Account[]>) => {
      observer.next([]);
    }));

    component.ngOnInit();
    fixture.detectChanges();
    tick;

    expect(component.listAccount).toEqual([]);
    expect(crudService.listAccount).toHaveBeenCalled();
    expect(component.flag2).toEqual(true);
    expect(component.flag1).toEqual(false);
  }));

});
