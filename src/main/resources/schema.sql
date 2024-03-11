create table if not exists notes
(
    id INT AUTO_INCREMENT primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    creation_date timestamp not null
);