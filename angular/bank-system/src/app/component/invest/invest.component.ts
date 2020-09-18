import { Component, OnInit } from '@angular/core';
import { CrudService } from '../../services/crud.service';
import { Investment } from '../../models/investment';
import { from } from 'rxjs';

@Component({
  selector: 'app-invest',
  templateUrl: './invest.component.html',
  styleUrls: ['./invest.component.css']
})
export class InvestComponent implements OnInit {

  public fundName;
  public account;
  public amount;

  public submit=false;
  public response;
  public flag=true;
  public errorMsg;
  public funds=["AAA", "BBB", "CCC", "DDD"];
  public fundHasError=false;
  public flag1=true;
  public listAccount;


  public investment = new Investment(this.fundName, this.account, this.amount, localStorage.getItem('pan'));
  constructor(private crudService: CrudService) { }

  ngOnInit(): void {

    this.crudService.listAccount()
                                .subscribe(data=>{this.listAccount=data;
                                                    if(Object.keys(this.listAccount).length === 0) {
                                                      this.errorMsg="You not register any bank account";
                                                      this.submit=true;
                                                    }
                                                   }
                                            
                                );
                              
  }

  onSubmit() {
    this.submit = true;
    console.log(this.investment)
    this.crudService.investment(this.investment)
      .subscribe(data => {this.response = data;
                          this.flag=false;
                          },
        error => {
          if (localStorage.getItem('status') === "409")
            this.errorMsg = "Please provide valid account no";
        }

      );
    
  }

  validatefund(fundName) {
    if(fundName==="default"){ this.fundHasError=true; this.flag1=true;}
    else { this.fundHasError=false; this.flag1=false;}
  }

}
