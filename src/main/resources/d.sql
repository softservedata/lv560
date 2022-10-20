insert into users (id, email, gender, is_admin, password)
values (1, 'admin@mail.com', 1, true, '12345');
insert into users (id, email, gender, is_admin, password)
values (2, 'user@mail.com', 1, false, '12345');
insert into users (id, email, gender, is_admin, password)
values (3, 'johnyGuitar@mail.com', 2, false, '12345');
insert into users (id, email, gender, is_admin, password)
values (4, 'danMiller@mail.com', 2, false, '12345');
insert into users (id, email, gender, is_admin, password)
values (5, 'stanLee@mail.com', 2, false, '12345');

insert into rooms (id, name)
values (1, 'saturn');

insert into seats (id, number, room_id)
values (1, 1, 1);
insert into seats (id, number, room_id)
values (2, 2, 1);
insert into seats (id, number, room_id)
values (3, 3, 1);
insert into seats (id, number, room_id)
values (4, 4, 1);
insert into seats (id, number, room_id)
values (5, 5, 1);
insert into seats (id, number, room_id)
values (6, 6, 1);
insert into seats (id, number, room_id)
values (7, 7, 1);
insert into seats (id, number, room_id)
values (8, 8, 1);
insert into seats (id, number, room_id)
values (9, 9, 1);
insert into seats (id, number, room_id)
values (10, 10, 1);

insert into rooms (id, name)
values (2, 'venus');

insert into seats (id, number, room_id)
values (11, 1, 2);
insert into seats (id, number, room_id)
values (12, 2, 2);
insert into seats (id, number, room_id)
values (13, 3, 2);
insert into seats (id, number, room_id)
values (14, 4, 2);
insert into seats (id, number, room_id)
values (15, 5, 2);
insert into seats (id, number, room_id)
values (16, 6, 2);
insert into seats (id, number, room_id)
values (17, 7, 2);
insert into seats (id, number, room_id)
values (18, 8, 2);
insert into seats (id, number, room_id)
values (19, 9, 2);
insert into seats (id, number, room_id)
values (20, 10, 2);

insert into movies (id, genre, name)
values (1, 'action', 'Bullet Train');
insert into movies (id, genre, name)
values (2, 'action', 'Thor Love And Thunder');
insert into movies (id, genre, name)
values (3, 'comedy', 'Hocus Pocus 2');
insert into movies (id, genre, name)
values (4, 'action', 'Doctor Strange in the Multiverse of Madness');
insert into movies (id, genre, name)
values (5, 'action', 'THE BATMAN');

insert into sessions (id, date, time, room_id)
values (1, '2022-10-20', '11:30:00', 1);
insert into sessions (id, date, time, room_id)
values (2, '2022-10-20', '14:30:00', 1);
insert into sessions (id, date, time, room_id)
values (3, '2022-10-20', '17:30:00', 1);

insert into sessions (id, date, time, room_id)
values (4, '2022-10-21', '11:30:00', 1);
insert into sessions (id, date, time, room_id)
values (5, '2022-10-21', '14:30:00', 1);
insert into sessions (id, date, time, room_id)
values (6, '2022-10-21', '17:30:00', 1);

insert into sessions (id, date, time, room_id)
values (7, '2022-10-22', '11:30:00', 1);
insert into sessions (id, date, time, room_id)
values (8, '2022-10-22', '14:30:00', 1);
insert into sessions (id, date, time, room_id)
values (9, '2022-10-22', '17:30:00', 1);

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

insert into books (id, session_id, user_id)
values (1, 1, 2);
insert into books (id, session_id, user_id)
values (2, 1, 3);
insert into books (id, session_id, user_id)
values (3, 2, 3);
insert into books (id, session_id, user_id)
values (4, 3, 3);

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