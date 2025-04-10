CREATE TABLE IF NOT EXISTS customer (
  customer_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  mobile_number VARCHAR(20) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS account (
  customer_id INT NOT NULL,
  account_number INT PRIMARY KEY AUTO_INCREMENT,
  account_type VARCHAR(100) NOT NULL,
  branch_address VARCHAR(250) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  updated_by VARCHAR(50) DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
