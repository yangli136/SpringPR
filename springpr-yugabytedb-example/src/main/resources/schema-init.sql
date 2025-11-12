DROP
    SCHEMA IF EXISTS crmd CASCADE;

CREATE
    SCHEMA IF NOT EXISTS crmd;
SET
search_path TO crmd;

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
            mer_id TEXT,
            doc JSONB NOT NULL,
            creat_ts TIMESTAMP,
            lst_updt_ts TIMESTAMP,
            PRIMARY KEY(
                mer_id ASC
            )
        );

DROP
    TABLE
        IF EXISTS industry;

DROP
    INDEX IF EXISTS idx_lst_updt_ts;

CREATE
    INDEX idx_lst_updt_ts ON
    merchant(
        lst_updt_ts DESC
    ) INCLUDE(mer_id);

CREATE
    TABLE
        industry(
            sic8_cd TEXT,
            doc JSONB,
            creat_ts TIMESTAMP,
            lst_updt_ts TIMESTAMP,
            PRIMARY KEY(
                sic8_cd ASC
            )
        );
