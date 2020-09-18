import { Component, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import { CrudService } from '../../services/crud.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-register-account',
  templateUrl: './register-account.component.html',
  styleUrls: ['./register-account.component.css']
})
export class RegisterAccountComponent implements OnInit {

  public accountNo;
  public ifcCode;
  public bankName;
  public micrCode;
  public errorMsg;
  public contact;
  public submit = false;
  public flag=true;
  public response;


  public account = new Account(this.accountNo, this.ifcCode, this.bankName, this.micrCode, localStorage.getItem('pan'));
  constructor(private crudService: CrudService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submit = true;
    console.log(this.account);
    this.crudService.accountRegister(this.account, this.contact)
      .subscribe(data => {this.response = data;
                          this.flag=false;
        },
        error => { 
          if (localStorage.getItem('status') === "400")
            this.errorMsg = "You cant register more than 4 bank account";
          else if (localStorage.getItem('status') === "409")
            this.errorMsg = "Account is already registerd";
          else if (localStorage.getItem('status')==="404")
            this.errorMsg = "Please provide registerd mobile no"
        }

      );
  }

}
