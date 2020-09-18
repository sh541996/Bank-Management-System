import { Component, OnInit } from '@angular/core';
import { CrudService } from '../../services/crud.service';

@Component({
  selector: 'app-unique-investment',
  templateUrl: './unique-investment.component.html',
  styleUrls: ['./unique-investment.component.css']
})
export class UniqueInvestmentComponent implements OnInit {

  public fundId: number;
  public errorMsg;
  public submit = false;
  public response;
  public flag;

  constructor(private crudService: CrudService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.fundId);
    this.submit = true;
    this.crudService.uniqueInvestment(this.fundId)
      .subscribe(data => {
        this.response = data;
        this.flag=true;
      },
        error => {
          if (localStorage.getItem('status') === "500")
            this.errorMsg = "You provide either wrong fund-id or you not made any investment";
        })
      
                                            
                            
  

}

}
