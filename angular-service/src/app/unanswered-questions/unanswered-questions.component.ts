import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-unanswered-questions',
  templateUrl: './unanswered-questions.component.html',
  styleUrls: ['./unanswered-questions.component.css']
})
export class UnansweredQuestionsComponent implements OnInit {

  questions;

  constructor(private trans:TransferServiceService,private app:AppComponent) { 
    if(this.app.checkLoggedIn== null){
      this.trans.GuestUnansweredQues().subscribe((data)=>{
        this.questions=data;
      })
    }else{
      this.trans.LoggedInUnansweredQues(this.app.emailid).subscribe((data)=>{
        this.questions=data;
      })
    }  
  }

  ngOnInit() {
  }

}
