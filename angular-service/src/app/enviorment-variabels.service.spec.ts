import { TestBed } from '@angular/core/testing';

import { EnviormentVariabelsService } from './enviorment-variabels.service';

describe('EnviormentVariabelsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnviormentVariabelsService = TestBed.get(EnviormentVariabelsService);
    expect(service).toBeTruthy();
  });
});
