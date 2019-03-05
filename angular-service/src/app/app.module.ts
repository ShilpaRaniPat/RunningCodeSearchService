import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MyQuestionsComponent } from './my-questions/my-questions.component';
import { MyAnswersComponent } from './my-answers/my-answers.component';
import { HomesectionComponent } from './homesection/homesection.component';
import { ProfileComponent } from './profile/profile.component';
import { TrendingQuestionsComponent } from './trending-questions/trending-questions.component';
import { UnansweredQuestionsComponent } from './unanswered-questions/unanswered-questions.component';
import { QuestionAnswerCardComponent } from './question-answer-card/question-answer-card.component';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import { QuestionCardComponent } from './question-card/question-card.component';
import { TransferServiceService } from './transfer-service.service';
import { CarouselComponent } from './carousel/carousel.component';
import { CarouselModule } from 'ngx-bootstrap';
import { MatFormFieldModule } from '@angular/material/form-field';
import { SearchResultComponent } from './search-result/search-result.component';
import { PostQuestionComponent } from './post-question/post-question.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DragDropModule } from '@angular/cdk/drag-drop';
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
} from '@angular/material';
import { A11yModule } from '@angular/cdk/a11y';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { CdkTableModule } from '@angular/cdk/table';
import { CdkTreeModule } from '@angular/cdk/tree';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { httpInterceptorProviders } from './auth/auth-interceptor';
import { LoginComponent } from './login/login.component';
import { NotifyComponent } from '../app/notify/notify.component';
import { WebSocketService } from './web-socket-service.service';
import { AcceptedQuesComponent } from './accepted-ques/accepted-ques.component';
import { QuestionCardAcceptedQuestionComponent } from './question-card-accepted-question/question-card-accepted-question.component';
import { NotifyAnswerCardComponent } from './notify-answer-card/notify-answer-card.component';
import { ChildComponent } from './child/child.component';
import { ParentComponent } from './parent/parent.component';



const routes: Routes = [{ path: 'myprofile', component: ProfileComponent },
{ path: 'questionAnswerCard', component: QuestionAnswerCardComponent },
{ path: '', component: HomesectionComponent },
{ path: 'postQuestion', component: PostQuestionComponent },
{ path: 'searchresult', component: ParentComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'login', component: LoginComponent },
{ path: 'notifyanswercard', component: NotifyAnswerCardComponent }]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MyQuestionsComponent,
    MyAnswersComponent,
    HomesectionComponent,
    ProfileComponent,
    TrendingQuestionsComponent,
    UnansweredQuestionsComponent,
    QuestionAnswerCardComponent,
    QuestionCardComponent,
    CarouselComponent,
    SearchResultComponent,
    PostQuestionComponent,
    RegisterComponent,
    LoginComponent,
    NotifyComponent,
    AcceptedQuesComponent,
    QuestionCardAcceptedQuestionComponent,
    NotifyAnswerCardComponent,
    ChildComponent,
    ParentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(routes),
    Ng2CarouselamosModule,
    MatCardModule,
    MatButtonModule,
    CarouselModule.forRoot(),
    MatFormFieldModule,
    MatChipsModule,
    HttpClientModule,
    ReactiveFormsModule,
    DragDropModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    DragDropModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatNativeDateModule,
    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    ScrollingModule,
    HttpClientModule
  ],
  providers: [TransferServiceService, httpInterceptorProviders, WebSocketService],
  bootstrap: [AppComponent]
})

export class AppModule { }