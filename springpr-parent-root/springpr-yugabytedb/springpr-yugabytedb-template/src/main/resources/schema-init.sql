DROP
    SCHEMA IF EXISTS springpr CASCADE;

CREATE
    SCHEMA IF NOT EXISTS springpr;
SET
search_path TO springpr;

DROP
    TABLE
        IF EXISTS employee;

CREATE
    TABLE
        IF NOT EXISTS employee(
            id TEXT PRIMARY KEY,
            name VARCHAR,
            email VARCHAR
        );

DROP
    TABLE
        IF EXISTS merchant;

CREATE
    TABLE
        merchant(
            merId TEXT PRIMARY KEY,
            jsonb_document JSONB,
            primSic8Cd TEXT,
            scndSic8Cd TEXT,
            scramMerId TEXT,
            legalNm TEXT,
            creat_ts TIMESTAMP,
            lst_updt_ts TIMESTAMP,
            localLstUpdtTs TIMESTAMP
        );

DROP
    TABLE
        IF EXISTS industry;

CREATE
    TABLE
        industry(
            sic8Cd TEXT PRIMARY KEY,
            jsonb_document JSONB,
            creat_ts TIMESTAMP,
            lst_updt_ts TIMESTAMP
        );
