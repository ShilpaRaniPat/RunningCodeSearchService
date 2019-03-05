import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-question-answer-card',
  templateUrl: './question-answer-card.component.html',
  styleUrls: ['./question-answer-card.component.css']
})
export class QuestionAnswerCardComponent implements OnInit {

  showAllCommentsQuestion = '';
  showAllCommentsAnswer = '';
  present;
  vote;
  ans;
  val = "";
  comm;
  comm1;
  val1 = "";
  answer;
  questionComm;
  QuestionCommentReply;
  commentAnswer1:string;
  replyanswercomment:string;



  constructor(private trans: TransferServiceService, private app: AppComponent, private route: Router, private http: HttpClient) {
    this.showAllCommentsQuestion = 'Show Details';
    this.showAllCommentsAnswer = 'Show Details';
  }

  ngOnInit() {
    this.present = this.trans.value;
    this.vote = this.present.upvote - this.present.downvote;
  }

  toggle(answer) {
    this.ans = answer;
    if (this.showAllCommentsAnswer === 'Hide Details') {
      this.showAllCommentsAnswer = 'Show Details';
    } else {
      this.showAllCommentsAnswer = 'Hide Details'
    }
  }

  toggle1() {
    if (this.showAllCommentsQuestion === 'Hide Details') {
      this.showAllCommentsQuestion = 'Show Details';
    } else {
      this.showAllCommentsQuestion = 'Hide Details'
    }
  }

  putval(comment: String) {
    this.comm = comment;
    if (this.val === "") {
      this.val = "some";
    } else {
      this.val = "";
    }
  }

  putval1(comment: String) {
    this.comm1 = comment;
    if (this.val1 === "") {
      this.val1 = "some";
    } else {
      this.val1 = "";
    }
  }
  //functions to use

  upvoteQuestion() {
    console.log(this.app.checkLoggedIn);
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('question upvote');
      this.http.put("http://localhost:8090/api/v1/question/upvote/" + this.present.questionId,{});
    }
  }

  downvoteQuestion() {
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('question downvote');
      this.http.put("http://localhost:8090/api/v1/question/downvote/"+this.present.questionId,{});
    }
  }

  commentQuestion() {
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('comment on question');
      console.log(this.answer);
      console.log('post answer for the question');
      this.http.put("http://localhost:8090/api/v1/question/answer/" + this.present.questionId,
        {
          "comment": this.questionComm,
          "timestamp": 9876543,
          "likes": 0,
          "user": {
            "email": "anjo@gmail.com",
            "firstName": "anjo",
            "imageurl": "https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
          },
          "replies": null
        },
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Comment added successfully");
          },
          error => {

            console.log("Error", error);

          })
    }

  }

replyQuestionComment(presentcomment:string) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('reply to comment of question');
      this.http.put("http://localhost:8090/api/v1/question/comment/reply/" + this.present.questionId,
      {
        "comment":presentcomment,
            "replies":[
              {
                "reply":this.QuestionCommentReply,
                "likes":0,
                "timestamp":64783,
                "user":{
                  "email":"anjo@gmail.com",
                  "firstName":"anjo",
                  "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
                }
              }
                ]
      },
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Reply made successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

upvoteAnswer(ans1) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('answer upvote');
    this.http.put("http://localhost:8080/api/v1/question/answer/upvote/"+this.present.questionId,{
      "answer": ans1
    })
  }
}

downvoteAnswer() {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('downvote answer');
    this.http.put("http://localhost:8090/api/v1/question/answer/downvote/"+this.present.questionId,{});
  }
}

commentAnswer(ans) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('comment on answer');
      this.http.put("http://localhost:8090/api/v1/question/answer/comment/" + this.present.questionId,
      {
        "answer": ans,
        "comments": [
      {
        "comment":this.commentAnswer1,
        "timestamp":4373648,
        "likes": 0,
        "user":{
              "email":"aishu@gmail.com",
              "firstName":"aishu",
              "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
            },
        "replies": null
      }
  ]
},
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Comment added to answer successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

replyAnswerComment(ans,comm) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('reply to comment of answer');
    this.http.put("http://localhost:8090/api/v1/question/answer/comment/reply/" + this.present.questionId,
    {
      "answer": ans,
      "comments": [
              {
                "comment":comm,
                "replies":[
                      {
                      "reply":this.replyanswercomment,
                      "likes":0,
                      "timestamp":64783,
                      "user":{
                          "email":"anjo@gmail.com",
                          "firstName":"anjo",
                          "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
                          }
                      }
                    ]
              }
            ]
},
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Reply made successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

postanswer() {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log(this.answer);
    console.log('post answer for the question');
    this.http.put("http://localhost:8090/api/v1/question/answer/" + this.present.questionId,
      {
        "answer": this.answer,
        "accepted": "false",
        "upvotes": 0,
        "views": 0,
        "timestamp": 445678,
        "user": {
          "email": " angel@gmail.com",
          "firstName": "angel",
          "imageUrl": "https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
        },
        "comments": null
      },
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'my-auth-token'
        })
      })
      .subscribe(
        data => {
          console.log("POST Request is successful ", data);
          alert("ANswer updated successfully");
        },
        error => {

          console.log("Error", error);

        })
  }
}
}
