import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { WelcomeComponent } from './welcome.component';
import { CrudService } from 'src/app/services/crud.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Observable, Observer } from 'rxjs';

let data={response:"shubham kumar"}

fdescribe('WelcomeComponent', () => {
  let component: WelcomeComponent;
  let fixture: ComponentFixture<WelcomeComponent>;
  let crudService: CrudService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomeComponent ],
      providers: [CrudService],
      imports: [HttpClientTestingModule,
      RouterTestingModule,
      FormsModule]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeComponent);
    component = fixture.componentInstance;
    crudService = TestBed.get(CrudService);
    fixture.detectChanges();
  });

  ///////////////////////////////
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //////////////////////////////
  it('ngOnInit() when login is successfully', fakeAsync(()=>{

    spyOn(crudService, 'welcome').and.returnValue(
      ( Observable.create((observer: Observer<any>) => {
        observer.next(data);
      })));

    component.ngOnInit();
    tick();
    fixture.detectChanges();

    expect(crudService.welcome).toHaveBeenCalled();
    expect(component.name).toEqual("shubham kumar");
  }));

});
