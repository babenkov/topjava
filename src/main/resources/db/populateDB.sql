DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

-- INSERT INTO meals (description, calories, dateTime)
-- VALUES ('Breakfast', 1000, now());

INSERT INTO "meals" (id, user_id, description, calories, datetime) VALUES
  (100110,100001,'Завтрак админа',888,'2015-05-11 06:36:56'),
  (100111,100001,'Обед админа',777,'2015-05-11 12:46:15'),
  (100112,100001,'Ужин админа',999,'2015-05-11 20:45:07');



INSERT INTO "meals" (id, user_id, description, calories, datetime) VALUES
  (100120,100000,'Завтрак пользователя',1000,'2015-07-11 11:27:34'),
  (100121,100000,'Обед пользователя',800,'2015-07-11 14:06:51'),
  (100122,100000,'Ужин пользователя',700,'2015-07-11 20:42:57'),
  (100123,100000,'Завтрак пользователя',1001,'2015-07-12 10:00:00'),
  (100124,100000,'Обед пользователя',801,'2015-07-12 14:00:00'),
  (100125,100000,'Ужин пользователя',701,'2015-07-12 20:00:00');


