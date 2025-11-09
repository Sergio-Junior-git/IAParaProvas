import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GabaritoCiadorComponent } from './gabarito-ciador.component';

describe('GabaritoCiadorComponent', () => {
  let component: GabaritoCiadorComponent;
  let fixture: ComponentFixture<GabaritoCiadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GabaritoCiadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GabaritoCiadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
