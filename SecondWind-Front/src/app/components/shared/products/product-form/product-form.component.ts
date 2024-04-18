import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessagesService } from '../../../../services/messages.service';
import { ProductService } from '../../../../services/product/product.service';
import { JwtService } from '../../../../services/jwt/jwt.service';
import { ProductCreateRequest } from './productCreateRequest';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css'
})
export class ProductFormComponent {
  creationError:string = '';
  productForm=this.formBuilder.group({
    title: ['', [Validators.required]],
    description: ['', Validators.required],
    price: [0, Validators.required]
  })

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private messagesService: MessagesService, 
    private productService: ProductService,
    private jwtService: JwtService,
  ) {}

  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }

  get title() {
    return this.productForm.controls.title;
  }

  get description() {
    return this.productForm.controls.description;
  }

  get price() {
    return this.productForm.controls.price;
  }

  createProduct() {
    if (this.productForm.valid) {
      let jwt: any = sessionStorage.getItem("token");
      let email = this.jwtService.getClaim(jwt, 'sub');
      let data: ProductCreateRequest = {
        sellerEmail: email,
        title: this.productForm.value.title? this.productForm.value.title : '',
        description: this.productForm.value.description? this.productForm.value.description : '', 
        price: this.productForm.value.price? this.productForm.value.price : 0
      }
      this.productService.createProduct(data).subscribe(
        {
          next: (response) => {
            console.log(response)
          },
          error: (error) => {
            console.log(error);
          },
          complete: () => {}
        }
      )
    }
  }
}
