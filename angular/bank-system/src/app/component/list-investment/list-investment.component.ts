import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/services/crud.service';
import { ExcelService } from '../../services/excel.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-list-investment',
  templateUrl: './list-investment.component.html',
  styleUrls: ['./list-investment.component.css']
})
export class ListInvestmentComponent implements OnInit {

  constructor(private crudService: CrudService, private excelService: ExcelService) { }

  public investments;
  public flag1 = false;
  public flag2 = false;
  public errorMsg;

  ngOnInit(): void {

    this.crudService.listInvestment()
                                .subscribe(data=>{this.investments=data;
                                                    if(Object.keys(this.investments).length === 0) this.flag2=true;
                                                    else this.flag1=true}
                                            
                                );
  }

  exportAsExcel(): void {
    this.excelService.exportAsExcelFile(this.investments, "investment");
  }



}
