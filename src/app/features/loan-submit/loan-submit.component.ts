import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { LoanApplicationInput, SubmissionResult } from '../../models/loan-models';
import { LoanApiService } from '../../services/loan-api.service';
import { LoanResultComponent } from './loan-result.component';

@Component({
  selector: 'app-loan-submit',
  standalone: true,
  imports: [CommonModule, FormsModule, LoanResultComponent],
  templateUrl: './loan-submit.component.html'
})
export class LoanSubmitComponent {
  private readonly api = inject(LoanApiService);

  form: LoanApplicationInput = {
    applicantName: 'Mike',
    loanAmount: 5000,
    loanPurpose: 'home-improvement',
    requestedTermMonths: 24,
    routePreference: 'auto'
  };

  result?: SubmissionResult;
  submitting = false;
  errorMessage = '';

  async submit(): Promise<void> {
    this.submitting = true;
    this.errorMessage = '';

    try {
      this.result = await this.api.submitLoanApplication(this.form);
    } catch (error) {
      this.result = undefined;
      this.errorMessage = error instanceof Error ? error.message : 'Request failed unexpectedly.';
    } finally {
      this.submitting = false;
    }
  }
}
