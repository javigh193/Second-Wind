import { Component, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesService } from '../../../services/messages.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnDestroy {

  loggedInUser: any = sessionStorage.getItem('username');

  constructor(private messagesService:MessagesService) {}

  /*ngOnInit(): void {
  }*/

  ngOnDestroy(): void {
    this.messagesService.clearMessages();
  }
}
