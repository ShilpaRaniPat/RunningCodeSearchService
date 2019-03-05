import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebSocketService } from './../web-socket-service.service';
import { Component, OnInit, Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input()
  check1: String;

  value: String;

  length1: number;
  length2: number = 0;

  info: any;

  public data: Array<any> = [];

  constructor(private trans: TransferServiceService, private token: TokenStorageService, private authService: AuthService, private router: Router, private webSocketService: WebSocketService, private http: HttpClient) { }


  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      email: this.token.getUsername()
    };
    // Open connection with server socket
    let stompClient = this.webSocketService.connect();
    stompClient.connect({}, frame => {


      // Subscribe to notification topic
      stompClient.subscribe('/queue/' + this.token.getUsername(), notifications => {

        // Update notifications attribute with the recent messsage sent from the server
        // let arr = new Array<any>(2);
        this.data.push(notifications.body);
        console.log("length is " + this.data.length);
        this.length1 = this.data.length - this.length2;
      })
    });

  }

  myfunction() {
    if (this.data.length < 6) {
      //this.data.splice(0,this.data.length-5);
      // this.length2=this.length1;
      this.length2 = this.length1;
      this.length1 = 0;
      console.log("lenght1 is " + this.data.length);
      console.log("lenght2 is " + this.length2);
    }
    else {
      this.data.splice(0, this.data.length - 5);
      this.length2 = 5;
      this.length1 = 0;
    }
  }


  putSearchVal() {
    this.trans.searchValue = this.value;
    console.log(this.value);
    this.http.post("http://52.66.134.21:8070/api/v1/" + this.value, {
    })
      .subscribe(
        data => {
          console.log("POST Request is successful ", data);
          alert("Your question is posted successfully");
          window.location.reload();
        },
        error => {
          console.log("Error", error);

        }

      );
  }

  logout() {
    this.token.signOut();
    this.router.navigate([""]);
  }


}
