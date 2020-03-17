use springblog_db;

INSERT INTO users (username, email, password)
VALUES ('test', 'test@test.com', 'password'),
       ('codeup', 'codeup@codeup.com', 'codeup');

INSERT INTO posts(title, description)
VALUES ('post 1', 'description 1'),
       ('post 2', 'description 2'),
       ('post 3', 'description 3')