CREATE TABLE users (
    id UUID NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_tb_users PRIMARY KEY (id),
    CONSTRAINT uc_tb_users_email UNIQUE (email)
);