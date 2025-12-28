use finance_db;

CREATE TABLE if not exists users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if not exists categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type ENUM('income', 'expense') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY unique_user_category (user_id, name),
    INDEX (user_id)
);

CREATE TABLE if not exists transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    type ENUM('income', 'expense') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    category_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    is_recurring BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    INDEX (user_id),
    INDEX (category_id),
    INDEX (date)
);

INSERT INTO categories (user_id, name, type) VALUES
(1, 'Salary', 'income'),
(1, 'Freelancing', 'income'),
(1, 'Food', 'expense'),
(1, 'Transport', 'expense'),
(1, 'Rent', 'expense');

INSERT INTO transactions (user_id, type, amount, category_id, date, description, is_recurring) VALUES
(1, 'income', 80000.00, 1, '2025-05-01', 'Monthly salary', TRUE),
(1, 'income', 15000.00, 2, '2025-05-03', 'Website project', FALSE),
(1, 'expense', 5000.00, 3, '2025-05-04', 'Groceries and dinner', TRUE),
(1, 'expense', 1200.00, 4, '2025-05-05', 'Taxi and fuel', FALSE),
(1, 'expense', 25000.00, 5, '2025-05-01', 'Monthly apartment rent', TRUE);



use finance_db;
-- select * from budgets b, users u, categories c where b.user_id = u.id and c.id = b.category_id and  c.user_id = u.id and c.type = "expense"  and month = date() order by amount desc limit 1 ;
-- select transactions.type, transactions.amount,monthname(transactions.date) AS transaction_month from users, transactions where users.id=1 and users.id=transactions.user_id
-- select c.name,c.type,b.amount from budgets b, categories c where b.category_id = c.id;
-- select id as transaction_id, type, date, description, amount from transactions
-- select sum(amount) as total_income from transactions where type = "income";
-- select count(amount) as total_transaction from transactions;
-- select description as top_income from transactions where type = 'income' order by amount asc limit 1;

select sum(amount) from transactions where user_id = 6