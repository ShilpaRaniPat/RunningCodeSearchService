import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifyAnswerCardComponent } from './notify-answer-card.component';

describe('NotifyAnswerCardComponent', () => {
  let component: NotifyAnswerCardComponent;
  let fixture: ComponentFixture<NotifyAnswerCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotifyAnswerCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotifyAnswerCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
