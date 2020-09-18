import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component'
import { WelcomeComponent } from './component/welcome/welcome.component';
import { RegisterAccountComponent } from './component/register-account/register-account.component';
import { ListRegisterAccountComponent } from './component/list-register-account/list-register-account.component';
import { InvestComponent } from './component/invest/invest.component';
import { ListInvestmentComponent } from './component/list-investment/list-investment.component';
import { UniqueInvestmentComponent } from './component/unique-investment/unique-investment.component';
import { from } from 'rxjs';

const routes: Routes = [

  { path: 'welcome', canActivate: [AuthGuard], component: WelcomeComponent },
  { path: 'uniqueInvestment', canActivate: [AuthGuard], component: UniqueInvestmentComponent},
  { path: 'listInvestment', canActivate: [AuthGuard], component: ListInvestmentComponent},
  { path: 'invest', canActivate: [AuthGuard], component: InvestComponent},
  { path: 'listRegisterAccount', canActivate: [AuthGuard], component: ListRegisterAccountComponent},
  { path: "registerBankAccount", canActivate: [AuthGuard], component: RegisterAccountComponent},
  { path : 'registeration', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[LoginComponent, RegisterComponent, WelcomeComponent]
