-- ============================================================
-- PhilHealth Member Registration System — Database Schema
-- ============================================================

CREATE DATABASE IF NOT EXISTS philhealth_db;
USE philhealth_db;

-- ── Member Table ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS MemberTable (
    pin_id              VARCHAR(12)     NOT NULL,
    purpose             VARCHAR(20)     NOT NULL,
    konsulta_provider   VARCHAR(100),
    member_name         VARCHAR(40)     NOT NULL,
    member_mother_name  VARCHAR(40)     NOT NULL,
    member_spouse_name  VARCHAR(40),
    date_of_birth       DATE            NOT NULL,
    birth_place         VARCHAR(100)    NOT NULL,
    sex                 CHAR(1)         NOT NULL CHECK (sex IN ('M', 'F')),
    civil_status        CHAR(2)         NOT NULL CHECK (civil_status IN ('S', 'M', 'A', 'LS', 'W')),
    citizenship         CHAR(2)         NOT NULL CHECK (citizenship IN ('F', 'FN', 'DC')),
    no_middle_name_flag BOOLEAN         DEFAULT FALSE,
    mononym_flag        BOOLEAN         DEFAULT FALSE,
    PhilSysIdNum        VARCHAR(16),
    tin_number          VARCHAR(12),
    direct_contributor  CHAR(3),
    indirect_contributor CHAR(3),
    profession          VARCHAR(35),
    income              DOUBLE,
    proof_of_income     VARCHAR(60),
    PRIMARY KEY (pin_id)
);

-- ── Dependent Table ───────────────────────────────────────────
CREATE TABLE IF NOT EXISTS DependentTable (
    dep_id                  INT             NOT NULL AUTO_INCREMENT,
    pin_id                  VARCHAR(12)     NOT NULL,
    dependent_name          VARCHAR(40)     NOT NULL,
    relationship_to_member  VARCHAR(20)     NOT NULL,
    dep_date_of_birth       DATE            NOT NULL,
    dep_citizenship         CHAR(2)         NOT NULL CHECK (dep_citizenship IN ('F', 'FN', 'DC')),
    dep_no_middle_name_flag BOOLEAN         DEFAULT FALSE,
    dep_mononym_flag        BOOLEAN         DEFAULT FALSE,
    permanent_disability_flag BOOLEAN       DEFAULT FALSE,
    PRIMARY KEY (dep_id),
    CONSTRAINT fk_dep_member FOREIGN KEY (pin_id)
        REFERENCES MemberTable (pin_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ── Contact Info Table ────────────────────────────────────────
CREATE TABLE IF NOT EXISTS ContactInfoTable (
    pin_id                  VARCHAR(12)     NOT NULL,
    permanent_home_address  VARCHAR(150)    NOT NULL,
    mail_address            VARCHAR(150),
    home_number             VARCHAR(11),
    mobile_number           VARCHAR(11)     NOT NULL,
    business_number         VARCHAR(10),
    email_address           VARCHAR(40),
    PRIMARY KEY (pin_id),
    CONSTRAINT fk_contact_member FOREIGN KEY (pin_id)
        REFERENCES MemberTable (pin_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
