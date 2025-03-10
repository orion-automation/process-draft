-- liquibase formatted sql

-- changeset eorion:1 dbms:oracle
CREATE TABLE ENHANCEMENT_PROCESS_DRAFT
(
    ID                      NUMBER(10,0),
    TENANT_TXT              NVARCHAR2(64)   DEFAULT '',
    RESOURCE_TYPE           CHAR(1)         DEFAULT '1',
    USER_ID                 NVARCHAR2(64)   NOT NULL ,
    PROCESS_DEFINITION_KEY  NVARCHAR2(255)  DEFAULT '',
    PROCESS_DEFINITION_NAME NVARCHAR2(255)  DEFAULT '',
    TASK_DEFINITION_KEY     NVARCHAR2(255)  DEFAULT '',
    TASK_DEFINITION_NAME    NVARCHAR2(255)  DEFAULT '',
    TASK_INSTANCE_ID        NVARCHAR2(64)   DEFAULT '',
    TYPE                    CHAR(1)         DEFAULT '1',
    DRAFT_NAME              VARCHAR(255)    DEFAULT '',
    FORM_DRAFT              NCLOB,
    CREATED_TS              NUMBER(13,0),
    UPDATED_TS              NUMBER(13,0),
    CREATE_BY_TXT           NVARCHAR2(20),
    UPDATED_BY_TXT          NVARCHAR2(20),
    PRIMARY KEY (ID)
);

-- changeset eorion:2 dbms:oracle
CREATE SEQUENCE ENHANCEMENT_PROCESS_DRAFT_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
NOCYCLE;

-- changeset eorion:3 dbms:oracle
CREATE OR REPLACE TRIGGER ENHANCEMENT_PROCESS_DRAFT_ID_INSERT
BEFORE INSERT ON ENHANCEMENT_PROCESS_DRAFT
FOR EACH ROW
BEGIN
  :NEW.id := ENHANCEMENT_PROCESS_DRAFT_SEQ.NEXTVAL;
END;
/