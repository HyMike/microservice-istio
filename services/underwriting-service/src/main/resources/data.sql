CREATE TABLE IF NOT EXISTS underwriting_decision (
  decision_id VARCHAR(64) PRIMARY KEY,
  application_id VARCHAR(64) NOT NULL,
  decision_outcome VARCHAR(64),
  decision_source VARCHAR(32),
  decision_reason VARCHAR(255)
);
