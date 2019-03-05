import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  signupInfo: SignUpInfo;
  errorMessage = '';
  FirstName = new FormControl('', [Validators.required]);
  LastName=new FormControl('');
  email = new FormControl('', [Validators.required, Validators.email]);
  password=new FormControl('', [Validators.required]);
  confirm=new FormControl('',[Validators.required]);
  toppings = new FormControl();
  toppingList: string[] = ['DataBinding', 'Pipes', 'Forms', 'Navigation', 'TypeScript', 'Testing','FundamentalArchitecture',''];
  constructor(private authService: AuthService,private route:Router) { }
  getErrorFnameMessage(){
    return this.FirstName.hasError('required') ? 'You must enter a value':'';
  }
 
  getpassErrorMessage(){
    return this.password.hasError('required') ? 'You must enter a value': '';
  }
 
  getconfirmErrorMessage(){
    if(this.password.value!=this.confirm.value) {
    return "Password Not Matching";
    }
  }
 
  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' :
            '';
  }

  ngOnInit() { }

  register():void {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.email.value,
      this.password.value,
      this.FirstName.value,
      this.LastName.value,
      this.toppings.value);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        alert("Registered Successfully!!!");
        this.route.navigate["/login"];

      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
      }
    );
  }
  backToLogin()
  {
    this.route.navigate([""]);
  }

}
