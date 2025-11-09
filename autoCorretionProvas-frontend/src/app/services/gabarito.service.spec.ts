import { TestBed } from '@angular/core/testing';

import { GabaritoService } from './gabarito.service';

describe('GabaritoService', () => {
  let service: GabaritoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GabaritoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
