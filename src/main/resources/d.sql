insert into users (email, gender, is_admin, password)
values ('admin@mail.com', 1, true, '$2a$10$BpHC.7n2qFWHNqML/7aZMejM/maDZsnDEB5DLAAWSDH0KWINZY84.');
insert into users (email, gender, is_admin, password)
values ('user@mail.com', 1, false, '$2a$10$BpHC.7n2qFWHNqML/7aZMejM/maDZsnDEB5DLAAWSDH0KWINZY84.');
insert into users (email, gender, is_admin, password)
values ('johnyGuitar@mail.com', 2, false, '$2a$10$BpHC.7n2qFWHNqML/7aZMejM/maDZsnDEB5DLAAWSDH0KWINZY84.');
insert into users (email, gender, is_admin, password)
values ('danMiller@mail.com', 2, false, '$2a$10$BpHC.7n2qFWHNqML/7aZMejM/maDZsnDEB5DLAAWSDH0KWINZY84.');
insert into users (email, gender, is_admin, password)
values ('stanLee@mail.com', 2, false, '$2a$10$BpHC.7n2qFWHNqML/7aZMejM/maDZsnDEB5DLAAWSDH0KWINZY84.');

insert into rooms (name)
values ('saturn');

insert into seats (number, room_id)
values (1, 1);
insert into seats (number, room_id)
values (2, 1);
insert into seats (number, room_id)
values (3, 1);
insert into seats (number, room_id)
values (4, 1);
insert into seats (number, room_id)
values (5, 1);
insert into seats (number, room_id)
values (6, 1);
insert into seats (number, room_id)
values (7, 1);
insert into seats (number, room_id)
values (8, 1);
insert into seats (number, room_id)
values (9, 1);
insert into seats (number, room_id)
values (10, 1);

insert into rooms (name)
values ('venus');

insert into seats (number, room_id)
values (1, 2);
insert into seats (number, room_id)
values (2, 2);
insert into seats (number, room_id)
values (3, 2);
insert into seats (number, room_id)
values (4, 2);
insert into seats (number, room_id)
values (5, 2);
insert into seats (number, room_id)
values (6, 2);
insert into seats (number, room_id)
values (7, 2);
insert into seats (number, room_id)
values (8, 2);
insert into seats (number, room_id)
values (9, 2);
insert into seats (number, room_id)
values (10, 2);

insert into movies (genre, name)
values ('action', 'Bullet Train');
insert into movies (genre, name)
values ('action', 'Thor Love And Thunder');
insert into movies (genre, name)
values ('comedy', 'Hocus Pocus 2');
insert into movies (genre, name)
values ('action', 'Doctor Strange in the Multiverse of Madness');
insert into movies (genre, name)
values ('action', 'THE BATMAN');

insert into sessions (date, time, room_id)
values ('2022-10-20', '11:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-20', '14:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-20', '17:30:00', 1);

insert into sessions (date, time, room_id)
values ('2022-10-21', '11:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-21', '14:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-21', '17:30:00', 1);

insert into sessions (date, time, room_id)
values ('2022-10-22', '11:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-22', '14:30:00', 1);
insert into sessions (date, time, room_id)
values ('2022-10-22', '17:30:00', 1);

insert into session_movie (session_id, movie_id)
values (1, 1);
insert into session_movie (session_id, movie_id)
values (2, 1);
insert into session_movie (session_id, movie_id)
values (3, 2);

insert into session_movie (session_id, movie_id)
values (4, 3);
insert into session_movie (session_id, movie_id)
values (5, 4);
insert into session_movie (session_id, movie_id)
values (6, 5);

insert into session_movie (session_id, movie_id)
values (7, 3);
insert into session_movie (session_id, movie_id)
values (8, 1);
insert into session_movie (session_id, movie_id)
values (9, 2);

insert into books (session_id, user_id)
values (1, 2);
insert into books (session_id, user_id)
values (1, 3);
insert into books (session_id, user_id)
values (2, 3);
insert into books (session_id, user_id)
values (3, 3);

insert into book_seat(book_id, seat_id)
values (1, 1);
insert into book_seat(book_id, seat_id)
values (2, 2);
insert into book_seat(book_id, seat_id)
values (2, 3);
insert into book_seat(book_id, seat_id)
values (2, 4);
insert into book_seat(book_id, seat_id)
values (3, 1);
insert into book_seat(book_id, seat_id)
values (3, 2);
insert into book_seat(book_id, seat_id)
values (3, 3);
insert into book_seat(book_id, seat_id)
values (4, 3);
insert into book_seat(book_id, seat_id)
values (4, 4);
insert into book_seat(book_id, seat_id)
values (4, 5);