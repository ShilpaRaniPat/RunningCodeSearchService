import { Component, OnInit, Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-question-card-accepted-question',
  templateUrl: './question-card-accepted-question.component.html',
  styleUrls: ['./question-card-accepted-question.component.css']
})
export class QuestionCardAcceptedQuestionComponent implements OnInit {

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
