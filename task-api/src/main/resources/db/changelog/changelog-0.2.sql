--liquibase formatted sql

--changeset umid:4

ALTER TABLE task_api.t_tasks RENAME COLUMN done_at TO completed_at;