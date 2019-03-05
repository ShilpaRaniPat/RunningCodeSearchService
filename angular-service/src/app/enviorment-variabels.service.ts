import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnviormentVariabelsService {

  trendingQuesGuest:string;

  trendingQuesLoggedIn:string;

  unansweredQuesGuest:string;

  unansweredQuesLoggedin:string;

  acceptedAnswersLoggedIn:string;

  searchServiceQuestion:string;

  User:string;
  
  constructor() {
    
    this.trendingQuesGuest="http://localhost:8081/guest/trending";

    this.trendingQuesLoggedIn="http://localhost:8081/member/trending?username=";

    this.unansweredQuesGuest="http://localhost:8081/guest/unanswered";

    this.unansweredQuesLoggedin="http://localhost:8081/member/unanswered/";

    this.acceptedAnswersLoggedIn="http://localhost:8081/member/acceptedanswers?username=";
  
    this.User="http://localhost:8052/User";

    this.searchServiceQuestion="http://localhost:8099/api/v1/topics";

   }
}
