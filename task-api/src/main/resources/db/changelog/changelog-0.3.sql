--liquibase formatted sql

--changeset umid:5
insert into task_api.t_users (id, email, first_name, last_name, username) values ('1', 'ikinane0@mit.edu', 'Ivette', 'Kinane', 'ikinane0');
insert into task_api.t_users (id, email, first_name, last_name, username) values ('2', 'otrustie1@dmoz.org', 'Orsa', 'Trustie', 'otrustie1');
insert into task_api.t_users (id, email, first_name, last_name, username) values ('3', 'kjirieck2@boston.com', 'Korney', 'Jirieck', 'kjirieck2');


insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (1, 'Blue', 'Red', 'NOT_COMPLETED', '2022-08-18 21:00:05', null);
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (2, 'Violet', null, 'NOT_COMPLETED', '2022-04-16 11:40:15', '2023-11-18 02:43:09');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (3, 'Teal', 'Khaki', 'NOT_COMPLETED', '2022-07-11 02:43:50', '2024-03-11 11:20:20');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (1, 'Turquoise', null, 'NOT_COMPLETED', '2022-10-28 23:21:49', null);
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (2, 'Crimson', 'Indigo', 'NOT_COMPLETED', '2023-03-14 10:47:32', '2024-01-06 01:19:30');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (3, 'Khaki', null, 'NOT_COMPLETED', '2022-07-26 12:19:48', '2023-09-08 05:00:30');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (1, 'Orange', 'Red', 'NOT_COMPLETED', '2022-10-09 12:15:20', '2023-10-15 16:57:39');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (2, 'Puce', 'Puce', 'NOT_COMPLETED', '2022-10-01 03:45:18', null);
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (3, 'Puce', 'Aquamarine', 'NOT_COMPLETED', '2023-03-26 14:53:06', '2023-07-18 19:08:04');
insert into task_api.t_tasks (user_id, title, description, status, created_at, completed_at) values (1, 'Orange', 'Khaki', 'NOT_COMPLETED', '2022-12-17 08:56:14', '2023-12-13 00:22:50');

--changeset umid:6
ALTER TABLE task_api.t_tasks ADD CONSTRAINT completed_at_after_created_at CHECK ( completed_at >= t_tasks.created_at );

