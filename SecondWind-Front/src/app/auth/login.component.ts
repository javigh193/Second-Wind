import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MessagesService } from '../services/messages.service';
import { LoginService } from './login.service';
import { LoginRequest } from './loginRequest';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnDestroy{
  loginError:string = '';
  loginForm=this.formBuilder.group({
    email:['', [Validators.required]],
    password:['', Validators.required],
  })

  constructor(
    private formBuilder:FormBuilder, 
    private router:Router,
    private messagesService:MessagesService, 
    private loginService: LoginService) {}
  
  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }

  get email() {
    return this.loginForm.controls.email;
  }

  get password() {
    return this.loginForm.controls.password;
  }

  login() {
    if (this.loginForm.valid){
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
        },
        error: (errorData) => {
          console.log(errorData);
          this.loginError=errorData;
        },
        complete: () => {
          this.router.navigateByUrl('/inicio');
          this.loginForm.reset();
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

}
