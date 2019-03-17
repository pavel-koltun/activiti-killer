CREATE TABLE connection_data (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    driver_class_name VARCHAR(128) NOT NULL,
    url VARCHAR(128) NOT NULL,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);