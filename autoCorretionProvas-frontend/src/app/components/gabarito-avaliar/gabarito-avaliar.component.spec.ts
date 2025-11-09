import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GabaritoAvaliarComponent } from './gabarito-avaliar.component';

describe('GabaritoAvaliarComponent', () => {
  let component: GabaritoAvaliarComponent;
  let fixture: ComponentFixture<GabaritoAvaliarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GabaritoAvaliarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GabaritoAvaliarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
