import { Component } from '@angular/core';

import { LoanSubmitComponent } from './features/loan-submit/loan-submit.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LoanSubmitComponent],
  template: `
    <main class="app-shell">
      <app-loan-submit></app-loan-submit>
    </main>
  `
})
export class AppComponent {}
