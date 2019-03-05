import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-trending-questions',
  templateUrl: './trending-questions.component.html',
  styleUrls: ['./trending-questions.component.css']
})
export class TrendingQuestionsComponent implements OnInit {

  questions;

  constructor(private trans:TransferServiceService,private app:AppComponent) {
    if(this.app.checkLoggedIn==null){
      this.trans.GuestTrendingQues().subscribe((data)=>
      {
        this.questions=data;
      })
    }else{
      this.trans.LoggedInTrendingQues(this.app.emailid).subscribe((data)=>{
        this.questions=data;
      })
    }

   }

  ngOnInit() {
  }

}
