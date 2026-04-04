import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';

import { environment } from '../../environments/environment';
import { LoanApplicationInput, SubmissionResult } from '../models/loan-models';

@Injectable({ providedIn: 'root' })
export class LoanApiService {
  private readonly http = inject(HttpClient);

  async submitLoanApplication(payload: LoanApplicationInput): Promise<SubmissionResult> {
    try {
      return await firstValueFrom(
        this.http.post<SubmissionResult>(
          `${environment.applicationServiceBaseUrl}/loan-applications`,
          payload
        )
      );
    } catch (error) {
      const message = error instanceof Error ? error.message : 'Unknown error';
      throw new Error(`Submission failed: ${message}`);
    }
  }
}
