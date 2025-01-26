-- liquibase formatted sql

-- changeset eorion:1 dbms:mysql
CREATE TABLE IF NOT EXISTS ENHANCEMENT_PROCESS_DRAFT
(
    ID
    SERIAL
    PRIMARY
    KEY,
    TENANT_TXT
    VARCHAR
(
    64
) DEFAULT '',
    RESOURCE_TYPE CHAR
(
    1
) NOT NULL DEFAULT '1',
    USER_ID VARCHAR
(
    64
) NOT NULL ,
    PROCESS_DEFINITION_KEY VARCHAR
(
    255
) DEFAULT '',
    PROCESS_DEFINITION_NAME VARCHAR
(
    255
) DEFAULT '',
    TASK_DEFINITION_KEY VARCHAR
(
    255
) DEFAULT '',
    TASK_DEFINITION_NAME VARCHAR
(
    255
) DEFAULT '',
    TASK_INSTANCE_ID VARCHAR
(
    64
) DEFAULT '',
    TYPE CHAR
(
    1
) NOT NULL DEFAULT '1',
    FORM_DRAFT LONGTEXT,
    DRAFT_NAME VARCHAR
(
    255
) default '',
    CREATED_TS BIGINT,
    UPDATED_TS BIGINT,
    CREATE_BY_TXT VARCHAR
(
    20
) default null,
    UPDATED_BY_TXT VARCHAR
(
    20
) default null
    );