import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { WelcomeComponent } from './component/welcome/welcome.component';
import { CrudService } from './services/crud.service';
import { AuthService } from './services/auth.service';
import { RegisterAccountComponent } from './component/register-account/register-account.component';
import { ListRegisterAccountComponent } from './component/list-register-account/list-register-account.component';
import { InvestComponent } from './component/invest/invest.component';
import { ListInvestmentComponent } from './component/list-investment/list-investment.component';
import { UniqueInvestmentComponent } from './component/unique-investment/unique-investment.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    WelcomeComponent,
    RegisterAccountComponent,
    ListRegisterAccountComponent,
    InvestComponent,
    ListInvestmentComponent,
    UniqueInvestmentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
  //   
],
  bootstrap: [AppComponent]
})
export class AppModule { }
