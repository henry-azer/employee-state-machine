--liquibase formatted sql

--changeset henry:20230617_create_employee_id_seq_table_and_seq

CREATE SEQUENCE IF NOT EXISTS public.employee_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.employee
(
    id                BIGINT                 NOT NULL DEFAULT nextval('employee_id_sequence'::regclass),
    name              CHARACTER VARYING(50) NOT NULL,
    status            TEXT                   NULL,
    events            TEXT                   NULL,

    created_date      TIMESTAMP,
    modified_date     TIMESTAMP,
    created_by        CHARACTER VARYING(100),
    modified_by       CHARACTER VARYING(100),
    marked_as_deleted BOOLEAN                         DEFAULT FALSE,

    CONSTRAINT employee_id_pk PRIMARY KEY (id)

) TABLESPACE pg_default;