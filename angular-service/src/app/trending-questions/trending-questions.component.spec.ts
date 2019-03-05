import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrendingQuestionsComponent } from './trending-questions.component';

describe('TrendingQuestionsComponent', () => {
  let component: TrendingQuestionsComponent;
  let fixture: ComponentFixture<TrendingQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrendingQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrendingQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
