export interface LoanApplicationInput {
  applicantName: string;
  loanAmount: number;
  loanPurpose: string;
  requestedTermMonths: number;
  routePreference?: 'primary' | 'alternate' | 'auto';
}

export interface SubmissionResult {
  applicationId: string;
  status: string;
  decisionOutcome: string;
  decisionSource: string;
  decisionReason: string;
}
