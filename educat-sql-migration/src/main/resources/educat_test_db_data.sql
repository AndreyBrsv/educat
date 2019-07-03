-- Данные для тестовой базы данных

-- *ПОЛЬЗОВАТЕЛИ*
-- Роли
INSERT INTO ec_user_roles (role_name, role_description) VALUES
('admin', 'administration of educat'),
('moderator', 'moderation of educat'),
('company', 'front compnay of educat'),
('tutor', 'tutors of educat'),
('user', 'educator');

-- Пользователи
INSERT INTO ec_users (user_role_id, email, login, pass, first_name, second_name, status) VALUES
            (1, 'andreybrsv@yandex.ru', 'AndreyBrsv', '12345', 'Andrey', 'Borisov', 'ACTIVE'),
            (1, 'ilyamikheev@yandex.ru', 'MikheevShow', '12345', 'Ilya', 'Mikheev', 'ACTIVE'),
            (1, 'khabibullinvasiliy@yandex.ru', 'GitKhab', '12345', 'Vasiliy', 'Khabibullin', 'ACTIVE'),
            (2, 'moderator@yandex.ru', 'Moderator123', '123', 'Moder', 'Moderov', 'DISABLED'),
            (3, 'super@vuz.ru', 'supervuz', '123', 'supervuz', 'supervuz', 'DELETED'),
            (4, 'repetitor@yandex.ru', 'Repetitor123', '123', 'Petr', 'Petrov', 'ACTIVE'),
            (5, 'student1@yandex.ru', 'User123', '123', 'Ivan', 'Ivanov', 'ACTIVE'),
            (6, 'student2@yandex.ru', 'User321', '321', 'Sergey', 'Sergeev', 'BLOCKED');

-- *ОБРАЗОВАТЕЛЬНЫЕ КУРСЫ*
-- Образовательные курсы



