import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule }   from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    model: any = {};
    public flag=false;
  
    constructor(
      private authService: AuthService, private router: Router
    ) {}
  
    ngOnInit() {
      this.authService.logout();
    }
  
    login() {
     // this.model.action = 'login';
      this.authService.loginForm(this.model).subscribe(response => {
        if (response.token !== null) {
          localStorage.setItem('pan',this.model.pan);
          this.authService.setUser(response);
        }
      }, error => {
        this.flag=true;
      });
    }


    register() {
      this.router.navigate(['/registeration']);
    }
  
  }