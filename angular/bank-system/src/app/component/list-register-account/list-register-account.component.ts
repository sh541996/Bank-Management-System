import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/services/crud.service';
import { isEmpty } from 'rxjs/operators';

@Component({
  selector: 'app-list-register-account',
  templateUrl: './list-register-account.component.html',
  styleUrls: ['./list-register-account.component.css']
})
export class ListRegisterAccountComponent implements OnInit {

  constructor(private crudService: CrudService) { }

  public listAccount;
  public flag1=false;
  public flag2=false;
  public errorMsg;

  ngOnInit(): void {

    this.crudService.listAccount()
                                .subscribe(data=>{this.listAccount=data;
                                                    if(Object.keys(this.listAccount).length === 0) this.flag2=true;
                                                    else this.flag1=true}
                                            
                                );
  }

}
