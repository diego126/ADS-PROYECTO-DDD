CREATE TABLE user_claim (
  user_claim_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  claim_type VARCHAR(100) NOT NULL,
  claim_value VARCHAR(100) NOT NULL,
  user_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_claim_id),
  CONSTRAINT FK_user_claim_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
  INDEX IX_user_claim_user_id (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;