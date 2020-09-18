import { Component, OnInit } from '@angular/core';
import { Registeration } from '../../models/registeration';
import { CrudService } from '../../services/crud.service';
import { combineAll } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public submit=false;
  public firstName;
  public lastName;
  public pan;
  public password;
  public contact;
  public mailId;
  public age=true;
  public age1=true;
  public registeration = new Registeration(this.firstName, this.lastName, this.pan, this.password, this.contact,this.mailId);
  public errorMsg;
  public successMsg;
  public success=true;
  


  constructor(private crudService: CrudService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submit=true;
    this.crudService.register(this.registeration)
                                .subscribe(data=>this.successMsg=data.name+" your registeration is successful",
                                 error => (this.success=false, this.errorMsg="user is already exist")
                                );
  }

  validateDob(dob) {
    //console.log(dob);
    const today=new Date();
    const birthDate= new Date(dob);
    let age = today.getFullYear()-birthDate.getFullYear();
    if(age<18) this.age=false;
    else if(age>18){
      this.age=true ;
      this.age1=false;
    }
}

}
