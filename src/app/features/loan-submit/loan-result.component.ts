import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

import { SubmissionResult } from '../../models/loan-models';

@Component({
  selector: 'app-loan-result',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './loan-result.component.html'
})
export class LoanResultComponent {
  @Input() result: SubmissionResult | null = null;
}
