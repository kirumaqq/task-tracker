--liquibase formatted sql

--changeset umid:1
CREATE SCHEMA task_api


--changeset umid:2
CREATE TABLE task_api.t_users
(
    id VARCHAR PRIMARY KEY ,
    email VARCHAR NOT NULL ,
    first_name VARCHAR,
    last_name VARCHAR,
    username VARCHAR,
    CONSTRAINT unique_email UNIQUE (email)
);


--changeset umid:3
CREATE TABLE task_api.t_tasks
(
    id BIGSERIAL PRIMARY KEY ,
    user_id VARCHAR NOT NULL ,
    title VARCHAR NOT NULL ,
    description VARCHAR(1000) ,
    status VARCHAR NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    done_at TIMESTAMP ,
    CONSTRAINT task_user_fk FOREIGN KEY (user_id) REFERENCES task_api.t_users(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
)