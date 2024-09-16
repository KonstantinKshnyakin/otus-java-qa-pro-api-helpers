CREATE TABLE IF NOT EXISTS users (id INTEGER, name VARCHAR(255), cource VARCHAR(255), email VARCHAR(255), age SMALLINT);

INSERT INTO users (id, name, cource, email, age) VALUES
(1, 'Test1', 'QA1', 'test1@test.test', 1),
(2, 'Test2', 'QA2', 'test2@test.test', 2),
(3, 'Test3', 'QA3', 'test3@test.test', 3);