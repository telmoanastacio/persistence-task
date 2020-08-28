DROP TABLE IF EXISTS client_snapshot;

CREATE TABLE client_snapshot(
    id bigint auto_increment not null,
    name varchar(255) not null,
    description varchar(255) not null,
    updated_timestamp bigint not null,
    primary key (id));

--INSERT INTO client_snapshot(name, description, updated_timestamp)
--VALUES('user1', 'actionType1', 0);
--
--INSERT INTO client_snapshot(name, description, updated_timestamp)
--VALUES('user2', 'actionType2', 0);
--
--INSERT INTO client_snapshot(name, description, updated_timestamp)
--VALUES('user3', 'actionType1', 0);