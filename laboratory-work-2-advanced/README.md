# Laboratory work 2 (Advanced)

**Author:** Khozhajinov Mykola, IM-21

## Variant

**Topic:** SQL Query Generator for CRUD Operations

This project is a Java-based implementation of a SQL query generator for Create, Read, Update, and Delete (CRUD) operations. It is designed to work with custom classes that represent database entities, using explicit field-to-column mappings with annotations and reflection. Additionally, the project includes a performance comparison between SQL query generation with and without reflection, using an Account class with id and balance fields as a demonstration.

## Environment

To work with this project, you need this tools installed on your machine:
* Java 16+
* Maven

## Usage

1. Clone the project from the remote repository:

`git clone https://github.com/kol-oss/java-basics.git`

2. Navigate to the directory for the second laboratory work:

`cd laboratory-work-2-advanced`

3. Execute Main file via Maven:

`mvn compile exec:java`

## Output

_Please note that execution times may vary based on your system configuration._

After running the main method in the Main class, you will get output similar to the following:

```shell
INSERT INTO users (user_id, age, username) VALUES (1, 10, 'Antony');
SELECT user_id, username, age FROM users WHERE user_id = 1
UPDATE users SET user_id = 1, age = 10 WHERE user_id = 1;
DELETE FROM users WHERE user_id = 1;
User with Reflection: 80

INSERT INTO categories (category_id, name) VALUES (0, 'Sport');
SELECT category_id, name FROM categories WHERE category_id = 0
UPDATE categories SET category_id = 0, name = 'Sport' WHERE category_id = 0;
DELETE FROM categories WHERE category_id = 0;
Category with Reflection: 5

INSERT INTO records (record_id, name, sum) VALUES (99, 'Gym rent', 99);
SELECT record_id, name, sum FROM records WHERE record_id = 99
UPDATE records SET record_id = 99, name = 'Gym rent' WHERE record_id = 99;
DELETE FROM records WHERE record_id = 99;
Record with Reflection: 4

INSERT INTO accounts (id, balance) VALUES (1, 10)
SELECT * FROM accounts WHERE id = 1
UPDATE accounts SET balance = 10 WHERE id = 1
DELETE FROM accounts WHERE id = 1
Account without Reflection: 30
```

This output demonstrates the SQL queries generated for CRUD operations, as well as the performance difference between implementations with and without reflection.