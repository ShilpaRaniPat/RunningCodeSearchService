import { Component, OnInit,Input } from '@angular/core';
import {TransferServiceService} from '../transfer-service.service';
import { __assign } from 'tslib';
@Component({
  selector: 'app-question-card',
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.css']
})
export class QuestionCardComponent implements OnInit {

  @Input()
  passedQuestions;
  
  desc;

  size;


  constructor(private trans:TransferServiceService) {
   }

  ngOnInit() {
    this.size=this.passedQuestions.answers.length;
    this.desc=(this.passedQuestions.description ? this.passedQuestions.description : '(No Description)');
    this.assign();
  }

  put(){
    this.trans.value=this.passedQuestions;
  }

  assign(){
    console.log(this.passedQuestions.description.length);
  }

}
