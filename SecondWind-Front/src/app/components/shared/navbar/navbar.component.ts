import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { LoginService } from '../../../auth/login.service';
import { JwtService } from '../../../services/jwt/jwt.service';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  
  userLoginOn: boolean = false;
  userAuthorities: string[] = [];

  constructor(private loginService:LoginService, private router:Router, private jwtService: JwtService) {}

  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe(
      {
        next: (userLoginOn) => {
          this.userLoginOn = userLoginOn;
        }
      }
    );

    this.loginService.currentUserData.subscribe(
      {
        next: (userData) => {
          if (userData != '') {
            this.userAuthorities = this.jwtService.getClaim(userData,"authorities")
            .map( (authority: { [x: string]: any; }) => authority["authority"] );
          } else {
            this.userAuthorities = [];
          }
        }
      }
    );
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigateByUrl('/inicio');
  }

}
