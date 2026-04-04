CREATE TABLE IF NOT EXISTS application_review (
  review_id VARCHAR(64) PRIMARY KEY,
  application_id VARCHAR(64) NOT NULL,
  normalized_amount DECIMAL(12,2),
  review_status VARCHAR(32)
);
