import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionAnswerCardComponent } from './question-answer-card.component';

describe('QuestionAnswerCardComponent', () => {
  let component: QuestionAnswerCardComponent;
  let fixture: ComponentFixture<QuestionAnswerCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionAnswerCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionAnswerCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
