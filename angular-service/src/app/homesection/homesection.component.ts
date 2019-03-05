import { WebSocketService } from './../web-socket-service.service';
import { Router } from '@angular/router';
import { AuthService } from './../auth/auth.service';
import { TokenStorageService } from './../auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-homesection',
  templateUrl: './homesection.component.html',
  styleUrls: ['./homesection.component.css']
})
export class HomesectionComponent implements OnInit {

  something;

  post;

  checkvalue;

  info: any;
  
  public data: Array < any >= [];

  constructor(private token: TokenStorageService,private authService: AuthService,private router :Router,private webSocketService:WebSocketService,private app:AppComponent) { }


  ngOnInit() {
    this.checkvalue=this.app.checkLoggedIn;
    this.info = {
      token: this.token.getToken(),
      email: this.token.getUsername()
    };
    // Open connection with server socket
    let stompClient = this.webSocketService.connect();
    stompClient.connect({}, frame => {
     

  // Subscribe to notification topic
        stompClient.subscribe('/queue/'+this.token.getUsername(), notifications => {

    // Update notifications attribute with the recent messsage sent from the server
           this.data.push(notifications.body);
           console.log("note:"+this.data)
           //this.notification =this.notification+','+ notifications.body;  
           console.log(this.data.length);  
           
        })
    });

  }

  myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
}
