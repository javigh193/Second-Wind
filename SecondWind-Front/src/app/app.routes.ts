import { Routes } from '@angular/router';
import { RegisterComponent } from './auth/register.component';
import { LoginComponent } from './auth/login.component';
import { authGuard } from './guards/auth.guard';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { HomeComponent } from './components/pages/home/home.component';
import { ProductFormComponent } from './components/shared/products/product-form/product-form.component';

export const routes: Routes = [
    {path: '', redirectTo:'/inicio', pathMatch:'full'},
    {path: 'inicio', component: HomeComponent},
    {
        path: 'dashboard', 
        component: DashboardComponent,
        canActivate: [authGuard]
    },
    {path: 'iniciar-sesion', component: LoginComponent},
    {path: 'registro', component: RegisterComponent},
    {
        path: 'crear-producto', 
        component: ProductFormComponent,
        canActivate: [authGuard]
    }
];
