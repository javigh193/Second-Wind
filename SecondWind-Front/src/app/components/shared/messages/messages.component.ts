import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MessagesService } from '../../../services/messages.service';

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css'
})
export class MessagesComponent implements OnDestroy {
  messages:string[] = [];
  subscription:Subscription = new Subscription;

  constructor(private messageService: MessagesService) {
    this.subscription = this.messageService.onMessage().subscribe(
      message => {
        if (message) {
          this.messages.push(message);
        } else {
          this.messages = [];
        }
      }
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  closeMessage($index:number) {
    this.messages.splice($index, 1);
  }
}
