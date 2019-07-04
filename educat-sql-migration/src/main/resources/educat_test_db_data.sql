-- Данные для тестовой базы данных

-- *ПОЛЬЗОВАТЕЛИ*
-- Роли
INSERT INTO ec_user_roles (user_role_id, role_name, role_description) VALUES
(1, 'admin', 'administration of educat'),
(2, 'moderator', 'moderation of educat'),
(3, 'company', 'front compnay of educat'),
(4, 'tutor', 'tutors of educat'),
(5, 'user', 'educator');

-- Пользователи
INSERT INTO ec_users (user_role_id, email, pass, first_name, second_name, status) VALUES
            (1, 'andreybrsv@yandex.ru', '12345', 'Andrey', 'Borisov', 'ACTIVE'),
            (1, 'ilyamikheev@yandex.ru', '12345', 'Ilya', 'Mikheev', 'ACTIVE'),
            (1, 'khabibullinvasiliy@yandex.ru', '12345', 'Vasiliy', 'Khabibullin', 'ACTIVE'),
            (2, 'moderator@yandex.ru', '123', 'Moder', 'Moderov', 'DISABLED'),
            (3, 'super@vuz.ru', '123', 'supervuz', 'supervuz', 'DELETED'),
            (4, 'repetitor@yandex.ru', '123', 'Petr', 'Petrov', 'ACTIVE'),
            (5, 'student1@yandex.ru', '123', 'Ivan', 'Ivanov', 'ACTIVE'),
            (6, 'student2@yandex.ru', '321', 'Sergey', 'Sergeev', 'BLOCKED');

-- *СТАТЬИ*
-- Статьи
INSERT INTO ec_articles (user_id, header, date)  VALUES
            (1, 'C++ OpenCL', TO_TIMESTAMP(:ts_val, '2019-06-19 19:27:22')),
            (2, 'Java Jetty Doc', CURRENT_TIMESTAMP),
            (3, 'Gulp frontend', TO_TIMESTAMP(:ts_val, '2019-06-25 11:23:25'));

INSERT INTO ec_article_comments (article_id, user_id, parent_comment_id, content, date) VALUES
            (2, 1, 0, 'Nice!', CURRENT_TIMESTAMP),
            (2, 3, 0, 'Interesting!', TO_TIMESTAMP(:ts_val, '2019-06-25 11:23:25')),
            (2, 2, 1, 'thank you', TO_TIMESTAMP(:ts_val, '2019-06-19 19:27:22'));

INSERT INTO ec_user_bookmarked_articles (user_id, article_id) VALUES
            (2, 3);