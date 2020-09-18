import { Component, OnInit } from '@angular/core';
import { CrudService } from '../../services/crud.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  public name;
  constructor(private crudService: CrudService) { }

  ngOnInit(){
    this.crudService.welcome()
          .subscribe(data=>{console.log(data);this.name=data.response});
  }

}
